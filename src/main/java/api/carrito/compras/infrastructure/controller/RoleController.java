package api.carrito.compras.infrastructure.controller;

import api.carrito.compras.domain.dto.role.RoleWithPrivilegesRequest;
import api.carrito.compras.domain.exception.PageableGeneralResponseModel;
import api.carrito.compras.domain.model.GeneralResponseModel;
import api.carrito.compras.domain.usecase.RoleService;
import api.carrito.compras.infrastructure.RoutesMapping;
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
 * RoleController class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = RoutesMapping.URL_ROLES_V1)
public class RoleController {

    private final RoleService roleService;

    /**
     * Gets all roles.
     *
     * @param page the page
     * @param size the size
     * @return the all
     */
    @PreAuthorize("hasAuthority('BROWSE_ROLE')")
    @GetMapping
    public ResponseEntity<PageableGeneralResponseModel> getAllRoles(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        return new ResponseEntity<>(roleService.getAllRoles(page, size), HttpStatus.OK);
    }

    /**
     * Gets role.
     *
     * @param id the id
     * @return the role
     */
    @PreAuthorize("hasAuthority('READ_ROLE')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<GeneralResponseModel> getRole(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(roleService.getRole(id), HttpStatus.OK);
    }

    /**
     * Edit role.
     *
     * @param id          the id
     * @param roleWithPrivilegesRequest the role request
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('EDIT_ROLE')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<GeneralResponseModel> editRole(@PathVariable(name = "id") Long id, @Valid @RequestBody RoleWithPrivilegesRequest roleWithPrivilegesRequest) {

        return new ResponseEntity<>(roleService.editRole(id, roleWithPrivilegesRequest), HttpStatus.OK);
    }

    /**
     * Add role.
     *
     * @param roleWithPrivilegesRequest the role request
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @PostMapping
    public ResponseEntity<GeneralResponseModel> addRole(@Valid @RequestBody RoleWithPrivilegesRequest roleWithPrivilegesRequest) {

        return new ResponseEntity<>(roleService.addRole(roleWithPrivilegesRequest), HttpStatus.CREATED);
    }

    /**
     * Delete role .
     *
     * @param id the id
     * @return the response entity
     */
    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GeneralResponseModel> deleteRole(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(roleService.deleteRole(id), HttpStatus.OK);
    }


    /**
     * Get all users.
     *
     * @param id the id
     * @return the all users
     */
    @PreAuthorize("hasAuthority('BROWSE_ROLE')")
    @GetMapping(value = "/{id}/users")
    public ResponseEntity<GeneralResponseModel> getAllUsers(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(roleService.getAllUsers(id), HttpStatus.OK);
    }
}
