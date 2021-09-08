package api.carrito.compras.infrastructure.controller;

import api.carrito.compras.domain.dto.auth.LoginUserRequest;
import api.carrito.compras.domain.dto.auth.RegisterUserRequest;
import api.carrito.compras.domain.model.GeneralResponseModel;
import api.carrito.compras.domain.usecase.AuthService;
import api.carrito.compras.infrastructure.RoutesMapping;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Authentication controller
 */
@RestController
@RequestMapping(value = RoutesMapping.URL_AUTH_V1)
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Signup
     *
     * @param registerUserRequest the register user request
     * @return the response entity
     */
    @PostMapping(value = "/signup")
    public ResponseEntity<GeneralResponseModel> signup(@Valid @RequestBody RegisterUserRequest registerUserRequest) {

        return new ResponseEntity<>(authService.signup(registerUserRequest), HttpStatus.CREATED);
    }

    /**
     * Verify account (activete)
     *
     * @param token the token
     * @return the response entity
     */
    @GetMapping(value = "/account-verification/{token}")
    public ResponseEntity<GeneralResponseModel> verifyAccount(@PathVariable(name = "token") String token) {

        return new ResponseEntity<>(authService.VerifyAccount(token), HttpStatus.OK);
    }
    
    /**
     * Login.
     *
     * @param loginUserRequest the login user request
     * @return the response entity
     */
    @PostMapping(value = "/login")
    public ResponseEntity<GeneralResponseModel> login(@Valid @RequestBody LoginUserRequest loginUserRequest) {

        return new ResponseEntity<>(authService.login(loginUserRequest), HttpStatus.OK);
    }
}
