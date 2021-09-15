package api.carrito.compras.infrastructure.controller;

import api.carrito.compras.domain.dto.user.UserRolesRequest;
import api.carrito.compras.domain.model.GeneralResponseModel;
import api.carrito.compras.domain.usecase.UserService;
import api.carrito.compras.infrastructure.RoutesMapping;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * UserController class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@RestController
@RequestMapping(value = RoutesMapping.URL_USERS_V1)
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping(value = "/{id}/roles")
    public ResponseEntity<GeneralResponseModel> updateRoles(@PathVariable(name = "id") Long id, @Valid @RequestBody UserRolesRequest userRolesRequest) {

        return new ResponseEntity<>(userService.updateRoles(userRolesRequest, id), HttpStatus.OK);
    }
}
