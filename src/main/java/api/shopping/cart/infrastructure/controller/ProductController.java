package api.shopping.cart.infrastructure.controller;

import api.shopping.cart.domain.dto.product.ProductRequest;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.usecase.ProductService;
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
 * ProductController class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@RestController
@RequestMapping(value = RoutesMapping.URL_PRODUCTS_V1)
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    /**
     * Gets all.
     *
     * @param page the page
     * @param size the size
     * @return the all
     */
    @PreAuthorize("hasAuthority('BROWSE_PRODUCT')")
    @GetMapping()
    public ResponseEntity<PageableGeneralResponseModel> getAll(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        return new ResponseEntity<>(productService.getAll(page, size), HttpStatus.OK);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('READ_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<GeneralResponseModel> get(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(productService.get(id), HttpStatus.OK);
    }

    /**
     * Edit response entity.
     *
     * @param id             the id
     * @param productRequest the product request
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('EDIT_PRODUCT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<GeneralResponseModel> edit(@PathVariable(name = "id") Long id, @Valid @RequestBody ProductRequest productRequest) {

        return new ResponseEntity<>(productService.edit(productRequest, id), HttpStatus.OK);
    }

    /**
     * Add response entity.
     *
     * @param productRequest the product request
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ResponseEntity<GeneralResponseModel> add(@Valid @RequestBody ProductRequest productRequest) {

        return new ResponseEntity<>(productService.add(productRequest), HttpStatus.CREATED);
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GeneralResponseModel> delete(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }
}