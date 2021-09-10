package api.carrito.compras.infrastructure.controller;

import api.carrito.compras.domain.exception.PageableGeneralResponseModel;
import api.carrito.compras.domain.usecase.RoleService;
import api.carrito.compras.infrastructure.RoutesMapping;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RoleController class
 *
 * @author edwin.lopezb.1297
 * @project compras
 * @since v1.0.0 - sep. 2021
 */

@RestController
@AllArgsConstructor
@RequestMapping(value = RoutesMapping.URL_ROLES_V1)
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<PageableGeneralResponseModel> getAll(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size){

        return new ResponseEntity<>(roleService.getAll(page, size), HttpStatus.OK);
    }
}
