package api.carrito.compras.domain.usecase;

import api.carrito.compras.domain.dto.auth.EmailRequest;
import api.carrito.compras.domain.dto.auth.LoginUserRequest;
import api.carrito.compras.domain.dto.auth.LogoutRequest;
import api.carrito.compras.domain.dto.auth.PasswordResetRequest;
import api.carrito.compras.domain.dto.auth.RefreshTokenRequest;
import api.carrito.compras.domain.dto.auth.RegisterUserRequest;
import api.carrito.compras.domain.model.GeneralResponseModel;
import api.carrito.compras.infrastructure.persistence.entity.User;

/**
 * AuthService interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

public interface AuthService {

    GeneralResponseModel signup(RegisterUserRequest registerUserRequest);
    String generateVerificationToken(User user);
    GeneralResponseModel VerifyAccount(String token);
    GeneralResponseModel login(LoginUserRequest loginUserRequest);
    GeneralResponseModel refreshToken(RefreshTokenRequest refreshTokenRequest);
    GeneralResponseModel logout(LogoutRequest logoutRequest);
    GeneralResponseModel sendEmailToResetPassword(EmailRequest emailRequest);
    GeneralResponseModel verifyTokenToResetPassword(String token);
    GeneralResponseModel resetPassword(PasswordResetRequest passwordResetRequest);
}
