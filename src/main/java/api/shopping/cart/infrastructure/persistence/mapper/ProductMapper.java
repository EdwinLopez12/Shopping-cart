package api.shopping.cart.infrastructure.persistence.mapper;

import api.shopping.cart.domain.dto.product.ProductResponse;
import api.shopping.cart.infrastructure.persistence.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ProductMapper class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Component
public class ProductMapper {

    public ProductResponse productToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        if (product.getId() != null) productResponse.setId(productResponse.getId());
        if (product.getCode() != null) productResponse.setCode(product.getCode());
        if (product.getName() != null) productResponse.setName(product.getName());
        if (product.getDescription() != null) productResponse.setDescription(product.getDescription());
        if (product.getWeight() != null) productResponse.setWeight(product.getWeight());
        if (product.getTotal() != null) productResponse.setTotal(product.getTotal());
        if (product.getPrice() != null) productResponse.setPrice(product.getPrice());
        // Retonar lista de categorias
        return productResponse;
    }

    public List<ProductResponse> productListToProductResponse(List<Product> productList) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : productList) {
            productResponses.add(productToProductResponse(product));
        }
        return productResponses;
    }
}
