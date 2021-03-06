package api.shopping.cart.infrastructure.controller;

import api.shopping.cart.domain.dto.user.UserDataRequest;
import api.shopping.cart.domain.dto.user.UserRolesRequest;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.usecase.UserService;
import api.shopping.cart.infrastructure.RoutesMapping;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * Update roles.
     *
     * @param id               the id
     * @param userRolesRequest the user roles request
     * @return the response entity
     */
    @ApiOperation("update the roles of a certain user")
    @PreAuthorize("hasAuthority('EDIT_ROLE')")
    @PutMapping(value = "/{id}/roles")
    public ResponseEntity<GeneralResponseModel> updateRoles(@PathVariable(name = "id") Long id, @Valid @RequestBody UserRolesRequest userRolesRequest) {

        return new ResponseEntity<>(userService.updateRoles(userRolesRequest, id), HttpStatus.OK);
    }

    /**
     * Delete roles.
     *
     * @param id               the id
     * @param userRolesRequest the user roles request
     * @return the response entity
     */
    @ApiOperation("Remove the role from a user")
    @PreAuthorize("hasAuthority('EDIT_ROLE')")
    @DeleteMapping(value = "/{id}/roles")
    public ResponseEntity<GeneralResponseModel> deleteRoles(@PathVariable(name = "id") Long id, @Valid @RequestBody UserRolesRequest userRolesRequest) {

        return new ResponseEntity<>(userService.deleteRoles(userRolesRequest, id), HttpStatus.OK);
    }

    /**
     * Add data.
     *
     * @param userDataRequest the user data request
     * @return the response entity
     */
    @ApiOperation("Add user data to a logged in user")
    @PreAuthorize("hasAuthority('EDIT_USER')")
    @PostMapping(value = "/data")
    public ResponseEntity<GeneralResponseModel> addData(@Valid @RequestBody UserDataRequest userDataRequest) {

        return new ResponseEntity<>(userService.addData(userDataRequest), HttpStatus.CREATED);
    }

    /**
     * Edit data.
     *
     * @param userDataRequest the user data request
     * @return the response entity
     */
    @ApiOperation("Edit user data to a logged in user")
    @PreAuthorize("hasAuthority('EDIT_USER')")
    @PutMapping(value = "/data")
    public ResponseEntity<GeneralResponseModel> editData(@Valid @RequestBody UserDataRequest userDataRequest) {

        return new ResponseEntity<>(userService.editData(userDataRequest), HttpStatus.OK);
    }
}
