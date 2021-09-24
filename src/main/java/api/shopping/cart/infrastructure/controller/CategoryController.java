package api.shopping.cart.infrastructure.controller;

import api.shopping.cart.domain.dto.category.CategoryRequest;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.usecase.CategoryService;
import api.shopping.cart.infrastructure.RoutesMapping;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * CategoryController class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@RestController
@RequestMapping(value = RoutesMapping.URL_CATEGORIES_V1)
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Gets all.
     *
     * @param page the page
     * @param size the size
     * @return the all
     */
    @PreAuthorize("hasAuthority('BROWSE_CATEGORY')")
    @GetMapping()
    public ResponseEntity<PageableGeneralResponseModel> getAll(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        return new ResponseEntity<>(categoryService.getAll(page, size), HttpStatus.OK);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('READ_CATEGORY')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<GeneralResponseModel> get(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(categoryService.get(id), HttpStatus.OK);
    }

    /**
     * Edit response entity.
     *
     * @param id              the id
     * @param categoryRequest the category request
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('EDIT_CATEGORY')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<GeneralResponseModel> edit(@PathVariable(name = "id") Long id, @Valid @RequestBody CategoryRequest categoryRequest) {

        return new ResponseEntity<>(categoryService.edit(categoryRequest, id), HttpStatus.OK);
    }

    /**
     * Add response entity.
     *
     * @param categoryRequest the product request
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('ADD_CATEGORY')")
    @PostMapping
    public ResponseEntity<GeneralResponseModel> add(@Valid @RequestBody CategoryRequest categoryRequest) {

        return new ResponseEntity<>(categoryService.add(categoryRequest), HttpStatus.CREATED);
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('DELETE_CATEGORY')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GeneralResponseModel> delete(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(categoryService.delete(id), HttpStatus.OK);
    }

    /**
     * Products by category response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('BROWSE_CATEGORY')")
    @GetMapping(value = "{id}/products")
    public ResponseEntity<GeneralResponseModel> productsByCategory(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(categoryService.productsByCategory(id), HttpStatus.OK);
    }

    /**
     * Deleted list response entity.
     *
     * @param page the page
     * @param size the size
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('BROWSE_CATEGORY')" + "&& hasAuthority('DELETE_CATEGORY')")
    @GetMapping(value = "/deleted")
    public ResponseEntity<PageableGeneralResponseModel> deletedList(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        return new ResponseEntity<>(categoryService.deletedList(page, size), HttpStatus.OK);
    }

    /**
     * Reactivate response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('BROWSE_CATEGORY')" + "&& hasAuthority('EDIT_CATEGORY')")
    @PutMapping(value = "/deleted/{id}")
    public ResponseEntity<GeneralResponseModel> reactivate(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(categoryService.reactivate(id), HttpStatus.OK);
    }
}
