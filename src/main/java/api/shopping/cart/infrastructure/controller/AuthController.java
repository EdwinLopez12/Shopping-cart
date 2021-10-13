package api.shopping.cart.infrastructure.controller;

import api.shopping.cart.domain.dto.auth.EmailRequest;
import api.shopping.cart.domain.dto.auth.LoginUserRequest;
import api.shopping.cart.domain.dto.auth.LogoutRequest;
import api.shopping.cart.domain.dto.auth.PasswordResetRequest;
import api.shopping.cart.domain.dto.auth.RefreshTokenRequest;
import api.shopping.cart.domain.dto.auth.RegisterUserRequest;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.usecase.AuthService;
import api.shopping.cart.infrastructure.RoutesMapping;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * AuthController class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
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
    @ApiOperation("Register new user on the application")
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
    @ApiOperation("Verify the account through a token sent via email")
    @GetMapping(value = "/account-verification/{token}")
    public ResponseEntity<GeneralResponseModel> verifyAccount(@PathVariable(name = "token") String token) {

        return new ResponseEntity<>(authService.verifyAccount(token), HttpStatus.OK);
    }

    /**
     * Login.
     *
     * @param loginUserRequest the login user request
     * @return the response entity
     */
    @ApiOperation("Login a user to the system, generating a JWT")
    @PostMapping(value = "/login")
    public ResponseEntity<GeneralResponseModel> login(@Valid @RequestBody LoginUserRequest loginUserRequest) {

        return new ResponseEntity<>(authService.login(loginUserRequest), HttpStatus.OK);
    }

    /**
     * Refresh Token.
     *
     * @param refreshTokenRequest the refresh token request
     * @return the response entity
     */
    @ApiOperation("Refresh the JWT to get access again when the token expires")
    @PostMapping(value = "/refresh-token")
    public ResponseEntity<GeneralResponseModel> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {

        return new ResponseEntity<>(authService.refreshToken(refreshTokenRequest), HttpStatus.OK);
    }

    /**
     * Logout.
     *
     * @param logoutRequest the logout request
     * @return the response entity
     */
    @ApiOperation("Close the user session in the application")
    @PostMapping(value = "/logout")
    public ResponseEntity<GeneralResponseModel> logout(@Valid @RequestBody LogoutRequest logoutRequest) {

        return new ResponseEntity<>(authService.logout(logoutRequest), HttpStatus.OK);
    }

    /**
     * Send email to reset password.
     *
     * @param emailRequest the email request
     * @return the response entity
     */
    @ApiOperation("Send an email to validate the user and allow the password change")
    @PostMapping(value = "/reset-password")
    public ResponseEntity<GeneralResponseModel> sendEmailToResetPassword(@Valid @RequestBody EmailRequest emailRequest) {

        return new ResponseEntity<>(authService.sendEmailToResetPassword(emailRequest), HttpStatus.OK);
    }

    /**
     * Verify token to reset password.
     *
     * @param token the token
     * @return the response entity
     */
    @ApiOperation("Validate the token sent via email to reset the password")
    @GetMapping(value = "/reset-password/{token}")
    public ResponseEntity<GeneralResponseModel> verifyTokenToResetPassword(@PathVariable(name = "token") String token) {

        return new ResponseEntity<>(authService.verifyTokenToResetPassword(token), HttpStatus.OK);
    }


    /**
     * Reset password response entity.
     *
     * @param passwordResetRequest the password reset request
     * @return the response entity
     */
    @ApiOperation("Update password")
    @PutMapping(value = "/reset-password")
    public ResponseEntity<GeneralResponseModel> resetPassword(@Valid @RequestBody PasswordResetRequest passwordResetRequest) {

        return new ResponseEntity<>(authService.resetPassword(passwordResetRequest), HttpStatus.OK);
    }
}
