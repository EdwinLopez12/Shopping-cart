package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.dto.auth.RegisterUserRequest;
import api.carrito.compras.domain.exception.ApiConflictException;
import api.carrito.compras.domain.exception.ApiNotFoundException;
import api.carrito.compras.domain.model.GeneralResponseModel;
import api.carrito.compras.domain.usecase.AuthService;
import api.carrito.compras.domain.repository.RoleDataEntity;
import api.carrito.compras.domain.repository.UserDataEntity;
import api.carrito.compras.domain.repository.VerificationTokenDataEntity;
import api.carrito.compras.infrastructure.persistence.entity.Role;
import api.carrito.compras.infrastructure.persistence.entity.User;
import api.carrito.compras.infrastructure.persistence.entity.VerificationToken;
import api.carrito.compras.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final String PASSWORDS_NOT_MATCH = "Las contraseñas no coinciden";
    private static final String EMAIL_ALREADY_EXIST = "El email ya está en uso";
    private static final String USERNAME_ALREADY_EXIST = "El nombre de usuario ya está en uso";
    private static final String ROLE_NOT_FOUND = "El rol no existe o no pudo ser encontrado";

    private final UserDataEntity userData;
    private final RoleDataEntity roleData;
    private final VerificationTokenDataEntity verificationTokenData;


    private final GeneralResponseModelMapper generalMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public GeneralResponseModel signup(RegisterUserRequest registerUserRequest) {
        String username = userData.findUsername(registerUserRequest.getUsername());
        String email = userData.findEmail(registerUserRequest.getEmail());
        if(username == null){
            if(email == null){
                if(registerUserRequest.getPassword().equals(registerUserRequest.getPasswordVerify())){
                    User user = User.builder()
                            .username(registerUserRequest.getUsername())
                            .email(registerUserRequest.getEmail())
                            .password(passwordEncoder.encode(registerUserRequest.getPassword()))
                            .isEnable(false)
                            .build();
                    Role role = roleData.findByName("USER_ROLE").orElseThrow(() -> new ApiNotFoundException(ROLE_NOT_FOUND));
                    user.addRole(role);
                    userData.save(user);
                    String token = generateVerificationToken(user);
                    // Mensaje con el token de activación
                    return generalMapper.responseToGeneralResponseModel(201, "signup", "Cuenta creada correctamente, por favor revisa el correo para activarla!", null, "Ok");
                }else{
                    throw new ApiConflictException(PASSWORDS_NOT_MATCH);
                }
            }else{
                throw new ApiConflictException(EMAIL_ALREADY_EXIST);
            }
        }else{
            throw new ApiConflictException(USERNAME_ALREADY_EXIST);
        }
    }

    @Override
    public String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        Instant instant = Instant.now();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .expiryDate(instant.plusMillis(60000))
                .build();
        verificationTokenData.save(verificationToken);
        return token;
    }
}
