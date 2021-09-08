package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.dto.auth.RegisterUserRequest;
import api.carrito.compras.domain.exception.ApiConflictException;
import api.carrito.compras.domain.exception.ApiNotFoundException;
import api.carrito.compras.domain.model.GeneralResponseModel;
import api.carrito.compras.domain.repository.RoleDataEntity;
import api.carrito.compras.domain.repository.UserDataEntity;
import api.carrito.compras.domain.repository.VerificationTokenDataEntity;
import api.carrito.compras.domain.usecase.AuthService;
import api.carrito.compras.domain.usecase.MailService;
import api.carrito.compras.domain.utils.MailData;
import api.carrito.compras.infrastructure.persistence.entity.Role;
import api.carrito.compras.infrastructure.persistence.entity.User;
import api.carrito.compras.infrastructure.persistence.entity.VerificationToken;
import api.carrito.compras.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private static final String PASSWORDS_NOT_MATCH = "Passwords don't match";
    private static final String EMAIL_ALREADY_EXIST = "The email is already in use";
    private static final String USERNAME_ALREADY_EXIST = "Username is already in use";
    private static final String USERNAME_NOT_FOUND = "The username doesn't exist or could not be found";
    private static final String ROLE_NOT_FOUND = "The role doesn't exist or could not be found";
    private static final String TOKEN_NOT_FOUND = "Token doesn't exist or could not be found";
    private static final String TOKEN_EXPIRED = "Token expired!";

    private final UserDataEntity userData;
    private final RoleDataEntity roleData;
    private final VerificationTokenDataEntity verificationTokenData;

    private final MailService mailService;

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
                    mailService.setUpEmailData(MailData.SUBJECT, MailData.TITLE, user.getEmail(), MailData.BODY_SIGNUP, MailData.END_POINT_SIGNUP, token);
                    return generalMapper.responseToGeneralResponseModel(201, "signup", "Account successfully created, please check the email to activate it!", null, "Ok");
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
                .expiryDate(instant.plusMillis(3600000))
                .build();
        verificationTokenData.save(verificationToken);
        return token;
    }

    @Override
    public GeneralResponseModel VerifyAccount(String token) {
        Instant now = Instant.now();
        VerificationToken verificationToken = verificationTokenData.findByToken(token).orElseThrow(() -> new ApiNotFoundException(TOKEN_NOT_FOUND));
        if (verificationToken.getExpiryDate().compareTo(now) >= 0) {
            String username = verificationToken.getUser().getUsername();
            User user = userData.findByUsernameOptional(username).orElseThrow(() -> new ApiNotFoundException(USERNAME_NOT_FOUND));
            user.setIsEnable(true);
            userData.save(user);
            return generalMapper.responseToGeneralResponseModel(200, "verify account", "Account activated successfully!", null, "Ok");
        }else{
            throw new ApiConflictException(TOKEN_EXPIRED);
        }
    }
}
