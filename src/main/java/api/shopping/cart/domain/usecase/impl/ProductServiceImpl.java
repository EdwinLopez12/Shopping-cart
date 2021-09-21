package api.shopping.cart.domain.usecase.impl;

import api.shopping.cart.domain.dto.product.ProductRequest;
import api.shopping.cart.domain.dto.product.ProductResponse;
import api.shopping.cart.domain.exception.ApiNotFoundException;
import api.shopping.cart.domain.exception.PageableDataResponseModel;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.repository.ProductRepository;
import api.shopping.cart.domain.usecase.ProductService;
import api.shopping.cart.infrastructure.RoutesMapping;
import api.shopping.cart.infrastructure.persistence.entity.Product;
import api.shopping.cart.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import api.shopping.cart.infrastructure.persistence.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * ProductServiceImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_NOT_FOUND = "The product doesn't exist or couldn't be found";
    private static final String PRODUCT_ALREADY_EXIST = "The product already exist";

    private final ProductRepository productRepository;

    private final GeneralResponseModelMapper generalMapper;
    private final ProductMapper productMapper;

    @Override
    public PageableGeneralResponseModel getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Page<Product> p = productRepository.findAll(pageable);
        return pageable(p, "get all");
    }

    private PageableGeneralResponseModel pageable(Page<Product> p, String type){

        List<ProductResponse> productResponses = productMapper.productListToProductResponse(p.getContent());

        PageableDataResponseModel pageableData = generalMapper.pageableResponseToPageableDataResponseModel(type, "listed products", productResponses, p.getSize(), p.getTotalPages(), p.getTotalElements(), p.getNumber());

        return generalMapper.pageableResponseToPageableGeneralResponseModel(p.getPageable().hasPrevious(), p.getPageable().previousOrFirst().getPageNumber(), RoutesMapping.URL_CATEGORIES_V1, pageableData);
    }


    @Override
    public GeneralResponseModel get(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(PRODUCT_NOT_FOUND));
        return generalMapper.responseToGeneralResponseModel(200, "get product", "Product found", Collections.singletonList(productMapper.productToProductResponse(product)), "Ok");
    }

    @Override
    public GeneralResponseModel add(ProductRequest productRequest) {
        return null;
    }

    @Override
    public GeneralResponseModel edit(ProductRequest productRequest, Long id) {
        return null;
    }

    @Override
    public GeneralResponseModel delete(Long id) {
        return null;
    }
}
