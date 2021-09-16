package api.shopping.cart.domain.usecase;

import api.shopping.cart.domain.dto.auth.EmailRequest;
import api.shopping.cart.domain.dto.auth.LoginUserRequest;
import api.shopping.cart.domain.dto.auth.LogoutRequest;
import api.shopping.cart.domain.dto.auth.PasswordResetRequest;
import api.shopping.cart.domain.dto.auth.RefreshTokenRequest;
import api.shopping.cart.domain.dto.auth.RegisterUserRequest;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.infrastructure.persistence.entity.User;

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
