package api.shopping.cart.domain.usecase.impl;

import api.shopping.cart.domain.dto.order.OrderProducts;
import api.shopping.cart.domain.dto.order.OrderRequest;
import api.shopping.cart.domain.dto.order.OrderResponse;
import api.shopping.cart.domain.exception.ApiConflictException;
import api.shopping.cart.domain.exception.ApiNotFoundException;
import api.shopping.cart.domain.exception.PageableDataResponseModel;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.repository.OrderProductRepository;
import api.shopping.cart.domain.repository.OrderRepository;
import api.shopping.cart.domain.repository.ProductRepository;
import api.shopping.cart.domain.repository.UserDataRepository;
import api.shopping.cart.domain.usecase.OrderService;
import api.shopping.cart.domain.usecase.UserService;
import api.shopping.cart.infrastructure.RoutesMapping;
import api.shopping.cart.infrastructure.persistence.OrderStatus;
import api.shopping.cart.infrastructure.persistence.entity.Order;
import api.shopping.cart.infrastructure.persistence.entity.OrderProduct;
import api.shopping.cart.infrastructure.persistence.entity.Product;
import api.shopping.cart.infrastructure.persistence.entity.User;
import api.shopping.cart.infrastructure.persistence.entity.UserData;
import api.shopping.cart.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import api.shopping.cart.infrastructure.persistence.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

/**
 * OrderServiceImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final String USER_NOT_FOUND = "The user doesn't exist or couldn't be found";
    private static final String ORDER_NOT_FOUND = "The order doesn't exist or couldn't be found";
    private static final String PRODUCT_NOT_FOUND = "The product doesn't exist or couldn't be found";
    private static final String PRODUCT_NOT_ENOUGH = "There are not enough products";
    private static final String ORDER_REQUEST_NOT_HAVE_PRODUCTS = "The request must have one or more products";
    private static final String ORDER_NOT_HAVE_PRODUCTS = "The order doesn't have products";

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserDataRepository userDataRepository;
    private final OrderProductRepository orderProductRepository;

    private final UserService userService;

    private final GeneralResponseModelMapper generalMapper;
    private final OrderMapper orderMapper;

    @Override
    public PageableGeneralResponseModel getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));
        Page<Order> o = orderRepository.findAllByDeletedAtIsNull(pageable);
        return pageable(o, "get all");
    }

    @Override
    public PageableGeneralResponseModel getAllByUser(Integer page, Integer size) {
        UserData userData = getUserData(userService.getCurrentUser());
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));
        Page<Order> o = orderRepository.findAllByDeletedAtIsNullAndUserData(pageable, userData);
        return pageable(o, "get all by user");
    }

    private PageableGeneralResponseModel pageable(Page<Order> o, String type){

        List<OrderResponse> orders = orderMapper.orderListToOrderResponse(o.getContent());

        PageableDataResponseModel pageableData = generalMapper.pageableResponseToPageableDataResponseModel(type, "listed orders", orders, o.getSize(), o.getTotalPages(), o.getTotalElements(), o.getNumber());

        return generalMapper.pageableResponseToPageableGeneralResponseModel(o.getPageable().hasPrevious(), o.getPageable().previousOrFirst().getPageNumber(), RoutesMapping.URL_ORDERS_V1, pageableData);
    }

    @Override
    public GeneralResponseModel get(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(ORDER_NOT_FOUND));
        OrderResponse orderResponse = orderMapper.orderToOrderResponse(order);
        return generalMapper.responseToGeneralResponseModel(200, "get order", "Order found", Collections.singletonList(orderResponse), "Ok");
    }

    @Transactional
    @Override
    public GeneralResponseModel edit(Long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(ORDER_NOT_FOUND));
        updateOrSaveOrderProducts(order, orderRequest, "EDIT");
        order.setUpdatedAt(Instant.now());
        orderRepository.save(order);
        OrderResponse orderResponse = orderMapper.orderToOrderResponse(order);
        return generalMapper.responseToGeneralResponseModel(200, "edit order", "Order edited", Collections.singletonList(orderResponse), "Ok");
    }

    private BigDecimal calcTotalPayment(Order order) {
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        if (!order.getOrderProducts().isEmpty()) {
            for (OrderProduct orderProduct : order.getOrderProducts()) {
                totalPrice = totalPrice.add(orderProduct.getValue().multiply(BigDecimal.valueOf(orderProduct.getAmount())));
            }
        }
        return totalPrice;
    }

    private void updateOrSaveOrderProducts(Order order, OrderRequest orderRequest, String option) {
        if (!orderRequest.getProducts().isEmpty()) {
            for (OrderProducts op : orderRequest.getProducts()) {

                Product product = productRepository.findById(op.getId()).orElseThrow(() -> new ApiNotFoundException(PRODUCT_NOT_FOUND));

                if (op.getAmount() > product.getTotal()) throw new ApiConflictException(PRODUCT_NOT_ENOUGH);

                OrderProduct orderProduct = OrderProduct.builder()
                        .order(order)
                        .product(product)
                        .amount(op.getAmount())
                        .value(product.getPrice())
                        .build();
                if (option.equals("ADD")) order.addOrderProduct(orderProduct);
                if (option.equals("EDIT")) order.updateAmountOrderProduct(orderProduct);

            }
            order.setTotalPayment(calcTotalPayment(order));
        }else{
            throw new ApiConflictException(ORDER_REQUEST_NOT_HAVE_PRODUCTS);
        }
    }

    private GeneralResponseModel generalResponse(Object o) {
        return generalMapper.responseToGeneralResponseModel(200, "add order", "Order created", Collections.singletonList(o), "Ok");
    }

    @Transactional
    @Override
    public GeneralResponseModel add(OrderRequest orderRequest) {
        UserData userData = getUserData(userService.getCurrentUser());
        Order order = orderRepository.findByUserDataAndStatusIsPending(userData, OrderStatus.PENDING);
        if (order != null){
            updateOrSaveOrderProducts(order, orderRequest, "ADD");
            orderRepository.save(order);
            OrderResponse orderResponse = orderMapper.orderToOrderResponse(order);
            return generalResponse(orderResponse);
        } else {
            Order orderToSave = Order.builder()
                    .userData(userData)
                    .status(OrderStatus.PENDING)
                    .createdAt(Instant.now())
                    .totalPayment(null)
                    .build();
            orderRepository.save(orderToSave);

            updateOrSaveOrderProducts(orderToSave, orderRequest, "ADD");
            orderRepository.save(orderToSave);

            OrderResponse orderResponse = orderMapper.orderToOrderResponse(orderToSave);
            return generalResponse(orderResponse);
        }
    }

    @Override
    public GeneralResponseModel delete(Long id) {
        // TODO : change order status to CANCELLED
        Order order = orderRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(ORDER_NOT_FOUND));
        order.setDeletedAt(Instant.now());
        orderRepository.save(order);
        return generalMapper.responseToGeneralResponseModel(200, "delete order", "Order deleted", null, "Ok");
    }


    @Override
    public GeneralResponseModel deleteProduct(Long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(ORDER_NOT_FOUND));
        if (orderRequest.getProducts().isEmpty()) throw new ApiConflictException(ORDER_REQUEST_NOT_HAVE_PRODUCTS);
        if (!order.getOrderProducts().isEmpty()) throw new ApiConflictException(ORDER_NOT_HAVE_PRODUCTS);

        for (OrderProducts ops : orderRequest.getProducts()) {
            boolean flag = false;
            for (OrderProduct op : order.getOrderProducts()) {
                if (ops.getId().equals(op.getProduct().getId())) flag = true;
            }
            if (!flag) throw new ApiNotFoundException(PRODUCT_NOT_FOUND);
        }

        for (OrderProducts op : orderRequest.getProducts()) {
            order.removeOrderProduct(op.getId());
            orderProductRepository.deleteByOrderIdAndProductId(order.getId(), op.getId());
        }
        order.setTotalPayment(calcTotalPayment(order));
        orderRepository.save(order);
        return generalMapper.responseToGeneralResponseModel(200, "delete product from order", "Product deleted", null, "Ok");
    }

    private UserData getUserData(User user) {
        return userDataRepository.findByUserId(user.getId()).orElseThrow(() -> new ApiNotFoundException(USER_NOT_FOUND));
    }
}
