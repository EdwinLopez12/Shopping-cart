package api.shopping.cart.infrastructure.persistence.mapper;

import api.shopping.cart.domain.dto.category.CategoryResponse;
import api.shopping.cart.infrastructure.persistence.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * CategoryMapper class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Component
public class CategoryMapper {

    public CategoryResponse categoryToCategoryResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        if (category.getId() != null) categoryResponse.setId(category.getId());
        if (category.getName() != null) categoryResponse.setName(category.getName());
        if (category.getDescription() != null) categoryResponse.setDescription(category.getDescription());
        return categoryResponse;
    }

    public List<CategoryResponse> categoryListToCategoryResponse(List<Category> categoryList) {
        List<CategoryResponse> categories = new ArrayList<>();
        for (Category category : categoryList) {
            categories.add(categoryToCategoryResponse(category));
        }
        return categories;
    }
}
