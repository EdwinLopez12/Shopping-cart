package api.shopping.cart.domain.usecase.impl;

import api.shopping.cart.domain.dto.product.ProductCategoryRequest;
import api.shopping.cart.domain.dto.product.ProductRequest;
import api.shopping.cart.domain.dto.product.ProductResponse;
import api.shopping.cart.domain.exception.ApiConflictException;
import api.shopping.cart.domain.exception.ApiNotFoundException;
import api.shopping.cart.domain.exception.PageableDataResponseModel;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.repository.CategoryRepository;
import api.shopping.cart.domain.repository.ProductRepository;
import api.shopping.cart.domain.usecase.ProductService;
import api.shopping.cart.infrastructure.RoutesMapping;
import api.shopping.cart.infrastructure.persistence.entity.Category;
import api.shopping.cart.infrastructure.persistence.entity.Product;
import api.shopping.cart.infrastructure.persistence.mapper.CategoryMapper;
import api.shopping.cart.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import api.shopping.cart.infrastructure.persistence.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    private static final String CATEGORY_NOT_FOUND = "The category doesn't exist or couldn't be found";

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final GeneralResponseModelMapper generalMapper;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

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
        Optional<Product> product = getProductByName(productRequest.getName());
        if (product.isPresent()) throw new ApiConflictException(PRODUCT_ALREADY_EXIST);
        Product newProduct = new Product();
        List<Category> categories = getCategories(productRequest.getCategories());
        newProduct = productMapper.productRequestToProduct(productRequest, newProduct, categories);
        productRepository.save(newProduct);
        return generalMapper.responseToGeneralResponseModel(200, "add product", "Product created", Collections.singletonList(productMapper.productToProductResponse(newProduct)), "Ok");

    }

    private Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    private List<Category> getCategories(List<ProductCategoryRequest> ids) {
        List<Category> categories = new ArrayList<>();
        for (ProductCategoryRequest pcr : ids) {
            categories.add(categoryRepository.findById(pcr.getId()).orElseThrow(() -> new ApiNotFoundException(CATEGORY_NOT_FOUND)));
        }
        return categories;
    }

    @Override
    public GeneralResponseModel edit(ProductRequest productRequest, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(PRODUCT_NOT_FOUND));
        Optional<Product> productName = getProductByName(productRequest.getName());
        if (productName.isPresent() && !productName.get().getId().equals(product.getId())) throw new ApiConflictException(PRODUCT_ALREADY_EXIST);
        List<Category> categories = getCategories(productRequest.getCategories());
        product = productMapper.productRequestToProduct(productRequest, product, categories);
        productRepository.save(product);
        return generalMapper.responseToGeneralResponseModel(200, "edit product", "Product edited", Collections.singletonList(productMapper.productToProductResponse(product)), "Ok");
    }

    @Override
    public GeneralResponseModel delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(PRODUCT_NOT_FOUND));
        product.setDeletedAt(Instant.now());
        productRepository.save(product);
        return generalMapper.responseToGeneralResponseModel(200, "delete product", "Product deleted", null, "Ok");
    }

    @Override
    public GeneralResponseModel categoriesByProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(PRODUCT_NOT_FOUND));
        List<Category> categories = categoryRepository.findByProductId(id);
        return generalMapper.responseToGeneralResponseModel(200, "categories by product", "Categories listed", Collections.singletonList(categoryMapper.categoryListToCategoryResponse(categories)), "Ok");
    }
}
