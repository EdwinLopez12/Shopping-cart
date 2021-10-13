package api.shopping.cart.app.boot;

import api.shopping.cart.domain.repository.CategoryRepository;
import api.shopping.cart.domain.repository.ProductRepository;
import api.shopping.cart.infrastructure.persistence.ProductStatus;
import api.shopping.cart.infrastructure.persistence.entity.Category;
import api.shopping.cart.infrastructure.persistence.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;


/**
 * ApiCategoriesAndProductsSetUpDataLoader class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Component
@AllArgsConstructor
@Order(4)
public class ApiCategoriesAndProductsSetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private static final String PRODUCT = "product ";
    private static final String CATEGORY = "category ";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        for (int i = 1; i<= 5; i++){
            Category category = createCategory(CATEGORY + i);
            createProduct(PRODUCT + i, String.valueOf(i), category);
        }
    }

    private Category createCategory(String name) {
        Category category = Category.builder()
                .name(name)
                .description("Description "+ name)
                .createdAt(Instant.now())
                .build();
        categoryRepository.save(category);
        return category;
    }

    private void createProduct(String name, String i, Category category) {
        Product product = Product.builder()
                .code(i)
                .name(name)
                .productStatus(ProductStatus.AVAILABLE)
                .description("description "+name)
                .price(BigDecimal.valueOf(10))
                .total(Integer.valueOf(i))
                .weight(Double.valueOf(i))
                .createdAt(Instant.now())
                .build();
        product.addCategory(category);
        productRepository.save(product);
    }
}
