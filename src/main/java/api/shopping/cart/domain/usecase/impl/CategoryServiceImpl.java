package api.shopping.cart.domain.usecase.impl;

import api.shopping.cart.domain.dto.category.CategoryRequest;
import api.shopping.cart.domain.dto.category.CategoryResponse;
import api.shopping.cart.domain.exception.ApiConflictException;
import api.shopping.cart.domain.exception.ApiNotFoundException;
import api.shopping.cart.domain.exception.PageableDataResponseModel;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.repository.CategoryRepository;
import api.shopping.cart.domain.repository.ProductRepository;
import api.shopping.cart.domain.usecase.CategoryService;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * CategoryServiceImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_NOT_FOUND = "The category doesn't exist or couldn't be found";
    private static final String CATEGORY_ALREADY_EXIST = "The category already exist";

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final GeneralResponseModelMapper generalMapper;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @Override
    public PageableGeneralResponseModel getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Page<Category> c = categoryRepository.findAllByDeletedAtIsNull(pageable);
        return pageable(c, "get all");
    }

    private PageableGeneralResponseModel pageable(Page<Category> c, String type){

        List<CategoryResponse> categories = categoryMapper.categoryListToCategoryResponse(c.getContent());

        PageableDataResponseModel pageableData = generalMapper.pageableResponseToPageableDataResponseModel(type, "listed categories", categories, c.getSize(), c.getTotalPages(), c.getTotalElements(), c.getNumber());

        return generalMapper.pageableResponseToPageableGeneralResponseModel(c.getPageable().hasPrevious(), c.getPageable().previousOrFirst().getPageNumber(), RoutesMapping.URL_CATEGORIES_V1, pageableData);
    }

    @Override
    public GeneralResponseModel get(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(CATEGORY_NOT_FOUND));
        CategoryResponse categoryResponse = categoryMapper.categoryToCategoryResponse(category);
        return generalMapper.responseToGeneralResponseModel(200, "get category", "Category found", Collections.singletonList(categoryResponse), "Ok");
    }

    @Override
    public GeneralResponseModel add(CategoryRequest categoryRequest) {
        Optional<Category> category = categoryRepository.findByName(categoryRequest.getName());
        if (category.isPresent()) throw new ApiConflictException(CATEGORY_ALREADY_EXIST);
        Category newCategory = new Category();
        newCategory = categoryMapper.categoryRequestToCategory(categoryRequest, newCategory);
        categoryRepository.save(newCategory);
        return generalMapper.responseToGeneralResponseModel(200, "add category", "Category created", Collections.singletonList(categoryMapper.categoryToCategoryResponse(newCategory)), "Ok");
    }

    @Override
    public GeneralResponseModel edit(CategoryRequest categoryRequest, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(CATEGORY_NOT_FOUND));
        Optional<Category> categoryName = categoryRepository.findByName(categoryRequest.getName());
        if (categoryName.isPresent() && !category.getId().equals(categoryName.get().getId())) throw new ApiConflictException(CATEGORY_ALREADY_EXIST);
        category = categoryMapper.categoryRequestToCategory(categoryRequest, category);
        categoryRepository.save(category);
        return generalMapper.responseToGeneralResponseModel(200, "edit category", "Category edited", Collections.singletonList(categoryMapper.categoryToCategoryResponse(category)), "Ok");
    }

    @Override
    public GeneralResponseModel delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(CATEGORY_NOT_FOUND));
        category.setDeletedAt(Instant.now());
        categoryRepository.save(category);
        return generalMapper.responseToGeneralResponseModel(200, "delete category", "Category deleted", null, "Ok");
    }

    @Override
    public GeneralResponseModel productsByCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) throw new ApiNotFoundException(CATEGORY_NOT_FOUND);
        List<Product> products = productRepository.findByCategoryId(id);
        return generalMapper.responseToGeneralResponseModel(200, "products by category id", "Products listed", Collections.singletonList(productMapper.productListToProductResponse(products)), "Ok");
    }

    @Override
    public PageableGeneralResponseModel deletedList(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Page<Category> c = categoryRepository.findAllByDeletedAtIsNotNull(pageable);
        return pageable(c, "get all deleted");
    }

    @Override
    public GeneralResponseModel reactivate(Long id) {
        Category category = categoryRepository.findByIdAndDeletedAtIsNotNull(id).orElseThrow(() -> new ApiNotFoundException(CATEGORY_NOT_FOUND));
        category.setDeletedAt(null);
        categoryRepository.save(category);
        return generalMapper.responseToGeneralResponseModel(200, "reactivate", "Category reactivated", Collections.singletonList(categoryMapper.categoryToCategoryResponse(category)), "Ok");
    }
}
