package api.carrito.compras.domain.usecase;

import api.carrito.compras.domain.dto.auth.RegisterUserRequest;
import api.carrito.compras.domain.model.GeneralResponseModel;
import api.carrito.compras.infrastructure.persistence.entity.User;

public interface AuthService {

    GeneralResponseModel signup(RegisterUserRequest registerUserRequest);
    String generateVerificationToken(User user);
    GeneralResponseModel VerifyAccount(String token);
}
