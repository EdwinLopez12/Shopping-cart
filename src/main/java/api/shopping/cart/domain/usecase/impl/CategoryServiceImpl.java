package api.shopping.cart.domain.usecase.impl;

import api.shopping.cart.domain.dto.category.CategoryRequest;
import api.shopping.cart.domain.dto.category.CategoryResponse;
import api.shopping.cart.domain.exception.PageableDataResponseModel;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.repository.CategoryRepository;
import api.shopping.cart.domain.usecase.CategoryService;
import api.shopping.cart.infrastructure.RoutesMapping;
import api.shopping.cart.infrastructure.persistence.entity.Category;
import api.shopping.cart.infrastructure.persistence.entity.Privilege;
import api.shopping.cart.infrastructure.persistence.mapper.CategoryMapper;
import api.shopping.cart.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private final CategoryRepository categoryRepository;

    private final GeneralResponseModelMapper generalMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public PageableGeneralResponseModel getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Page<Category> p = categoryRepository.findAll(pageable);
        return pageable(p, "get all");
    }

    private PageableGeneralResponseModel pageable(Page<Category> c, String type){

        List<CategoryResponse> categories = categoryMapper.categoryListToCategoryResponse(c.getContent());

        PageableDataResponseModel pageableData = generalMapper.pageableResponseToPageableDataResponseModel(type, "listed categories", categories, c.getSize(), c.getTotalPages(), c.getTotalElements(), c.getNumber());

        return generalMapper.pageableResponseToPageableGeneralResponseModel(c.getPageable().hasPrevious(), c.getPageable().previousOrFirst().getPageNumber(), RoutesMapping.URL_CATEGORIES_V1, pageableData);
    }

    @Override
    public GeneralResponseModel get(Long id) {
        return null;
    }

    @Override
    public GeneralResponseModel add(CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public GeneralResponseModel edit(CategoryRequest categoryRequest, Long id) {
        return null;
    }

    @Override
    public GeneralResponseModel delete(Long id) {
        return null;
    }
}
