package api.shopping.cart.infrastructure.controller;

import api.shopping.cart.domain.dto.order.OrderRequest;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.usecase.OrderService;
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
 * OrderController class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@RestController
@RequestMapping(value = RoutesMapping.URL_ORDERS_V1)
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Gets all.
     *
     * @param page the page
     * @param size the size
     * @return the all
     */
    @PreAuthorize("hasAuthority('BROWSE_ORDER')")
    @GetMapping
    public ResponseEntity<PageableGeneralResponseModel> getAll(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        return new ResponseEntity<>(orderService.getAll(page, size), HttpStatus.OK);
    }

    /**
     * Gets all by user.
     *
     * @param page the page
     * @param size the size
     * @return the all by user
     */
    @PreAuthorize("hasAuthority('BROWSE_ORDER')")
    @GetMapping(value = "/by-users")
    public ResponseEntity<PageableGeneralResponseModel> getAllByUser(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        return new ResponseEntity<>(orderService.getAllByUser(page, size), HttpStatus.OK);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('READ_ORDER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<GeneralResponseModel> get(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(orderService.get(id), HttpStatus.OK);
    }

    /**
     * Edit response entity.
     *
     * @param id           the id
     * @param orderRequest the order request
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('EDIT_ORDER')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<GeneralResponseModel> edit(@PathVariable(name = "id") Long id, @Valid @RequestBody OrderRequest orderRequest) {

        return new ResponseEntity<>(orderService.edit(id, orderRequest), HttpStatus.OK);
    }

    /**
     * Add response entity.
     *
     * @param orderRequest the order request
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('ADD_ORDER')")
    @PostMapping
    public ResponseEntity<GeneralResponseModel> add(@Valid @RequestBody OrderRequest orderRequest) {

        return new ResponseEntity<>(orderService.add(orderRequest), HttpStatus.CREATED);
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('DELETE_ORDER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GeneralResponseModel> delete(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(orderService.delete(id), HttpStatus.OK);
    }

    /**
     * Delete product response entity.
     *
     * @param id           the id
     * @param orderRequest the order request
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('DELETE_ORDER')" + "&& hasAuthority('DELETE_PRODUCT')")
    @PostMapping(value = "/product/{id}")
    public ResponseEntity<GeneralResponseModel> deleteProduct(@PathVariable(name = "id") Long id, @Valid @RequestBody OrderRequest orderRequest) {

        return new ResponseEntity<>(orderService.deleteProduct(id, orderRequest), HttpStatus.OK);
    }


}
