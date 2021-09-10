package api.carrito.compras.infrastructure.controller;

import api.carrito.compras.domain.exception.PageableGeneralResponseModel;
import api.carrito.compras.domain.usecase.PrivilegeService;
import api.carrito.compras.infrastructure.RoutesMapping;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * PrivilegeController class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */

@RestController
@AllArgsConstructor
@RequestMapping(value = RoutesMapping.URL_PRIVILEGES_V1)
public class PrivilegeController {

    private final PrivilegeService privilegeService;


    /**
     * Find all.
     *
     * @param page the page
     * @param size the size
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('BROWSE_PRIVILEGE')")
    @GetMapping
    public ResponseEntity<PageableGeneralResponseModel> findAll(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size){

        return new ResponseEntity<>(privilegeService.getAll(page, size), HttpStatus.OK);
    }
}
