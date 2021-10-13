package api.shopping.cart.infrastructure.controller;

import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.usecase.StateService;
import api.shopping.cart.infrastructure.RoutesMapping;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TownController class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@RestController
@RequestMapping(value = RoutesMapping.URL_TOWNS_V1)
@AllArgsConstructor
public class StateController {

    private final StateService stateService;

    /**
     * List towns by country
     *
     * @param country the country
     * @return the response entity
     */
    @ApiOperation("Get the cities, departments, states with a certain country")
    @PreAuthorize("hasAuthority('BROWSE_TOWN')")
    @GetMapping(value = "/{country}")
    public ResponseEntity<GeneralResponseModel> listTownsByCountry(@PathVariable(name = "country") String country) {

        return new ResponseEntity<>(stateService.listTownsByCountry(country), HttpStatus.OK);
    }


}
