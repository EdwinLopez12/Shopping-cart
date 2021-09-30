package api.shopping.cart.app.boot;

import api.shopping.cart.domain.exception.ApiNotFoundException;
import api.shopping.cart.domain.repository.OrderProductRepository;
import api.shopping.cart.domain.repository.OrderRepository;
import api.shopping.cart.domain.repository.ProductRepository;
import api.shopping.cart.domain.repository.StateRepository;
import api.shopping.cart.domain.repository.UserDataRepository;
import api.shopping.cart.domain.repository.UserRepository;
import api.shopping.cart.domain.utils.FormatDates;
import api.shopping.cart.infrastructure.persistence.OrderStatus;
import api.shopping.cart.infrastructure.persistence.entity.OrderProduct;
import api.shopping.cart.infrastructure.persistence.entity.Product;
import api.shopping.cart.infrastructure.persistence.entity.State;
import api.shopping.cart.infrastructure.persistence.entity.User;
import api.shopping.cart.infrastructure.persistence.entity.UserData;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * ApiOrderSetUpDataLoader class
 *
 * @author edwin.lopezb.1297
 * @project cart
 * @since v1.0.0 - sep. 2021
 */
@Component
@AllArgsConstructor
@Order(5)
public class ApiOrderSetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;
    private final StateRepository stateRepository;
    private final OrderProductRepository orderProductRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        User user1 = userRepository.findById(1L).orElseThrow(() -> new ApiNotFoundException("USER NOT FOUND"));
        User user2 = userRepository.findById(2L).orElseThrow(() -> new ApiNotFoundException("USER NOT FOUND"));
        State state = stateRepository.findByStateId(1L).orElseThrow(() -> new ApiNotFoundException("STATE NOT FOUND"));
        List<Product> products = new ArrayList<>();
        for (int i = 1; i<=5; i++) {
            Product product = productRepository.findById((long) i).orElseThrow(() -> new ApiNotFoundException("PRODUCT NOT FOUND"));
            products.add(product);
        }

        UserData userData1 = createUserData(user1, 1, state);
        createOrder(products, userData1);
        UserData userData2 = createUserData(user2, 2, state);
        createOrder(products, userData2);
    }

    private void createOrder(List<Product> products, UserData userData) {
        api.shopping.cart.infrastructure.persistence.entity.Order order = api.shopping.cart.infrastructure.persistence.entity.Order.builder()
                .status(OrderStatus.PAID)
                .date(FormatDates.instantToString(Instant.now()))
                .totalPayment(BigDecimal.valueOf(10000))
                .userData(userData)
                .createdAt(Instant.now())
                .build();
        orderRepository.save(order);
        for (Product product : products) {
            OrderProduct orderProduct = OrderProduct.builder()
                    .value(product.getPrice())
                    .amount(2)
                    .product(product)
                    .order(order)
                    .build();
            orderProductRepository.save(orderProduct);
        }
    }

    private UserData createUserData(User user, Integer b, State state) {
        UserData userData = UserData.builder()
                .nid("123456789"+b)
                .name("U = "+ user.getUsername())
                .lastName("L = "+ user.getUsername())
                .phone("123456789")
                .address("Cra 1 #10-15")
                .state(state)
                .build();
        userDataRepository.save(userData);
        return userData;
    }
}
