package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.dto.auth.AuthenticationResponse;
import api.carrito.compras.domain.dto.auth.EmailRequest;
import api.carrito.compras.domain.dto.auth.LoginUserRequest;
import api.carrito.compras.domain.dto.auth.LogoutRequest;
import api.carrito.compras.domain.dto.auth.RefreshTokenRequest;
import api.carrito.compras.domain.dto.auth.RegisterUserRequest;
import api.carrito.compras.domain.exception.ApiConflictException;
import api.carrito.compras.domain.exception.ApiNotFoundException;
import api.carrito.compras.domain.model.GeneralResponseModel;
import api.carrito.compras.domain.repository.PasswordResetRepository;
import api.carrito.compras.domain.repository.RoleRepository;
import api.carrito.compras.domain.repository.UserRepository;
import api.carrito.compras.domain.repository.VerificationTokenRepository;
import api.carrito.compras.domain.usecase.AuthService;
import api.carrito.compras.domain.usecase.MailService;
import api.carrito.compras.domain.usecase.RefreshTokenService;
import api.carrito.compras.domain.utils.FormatDates;
import api.carrito.compras.domain.utils.MailData;
import api.carrito.compras.infrastructure.persistence.entity.PasswordReset;
import api.carrito.compras.infrastructure.persistence.entity.Role;
import api.carrito.compras.infrastructure.persistence.entity.User;
import api.carrito.compras.infrastructure.persistence.entity.VerificationToken;
import api.carrito.compras.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import api.carrito.compras.infrastructure.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private static final String PASSWORDS_NOT_MATCH = "Passwords don't match";
    private static final String EMAIL_ALREADY_EXIST = "The email is already in use";
    private static final String EMAIL_NOT_FOUND = "The email doesn't exist or could not be found";
    private static final String USERNAME_ALREADY_EXIST = "Username is already in use";
    private static final String USERNAME_NOT_FOUND = "The username doesn't exist or could not be found";
    private static final String ROLE_NOT_FOUND = "The role doesn't exist or could not be found";
    private static final String TOKEN_NOT_FOUND = "Token doesn't exist or could not be found";
    private static final String TOKEN_EXPIRED = "Token expired!";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetRepository passwordResetRepository;

    private final MailService mailService;
    private final RefreshTokenService refreshTokenService;

    private final GeneralResponseModelMapper generalMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public GeneralResponseModel signup(RegisterUserRequest registerUserRequest) {
        String username = userRepository.findUsername(registerUserRequest.getUsername());
        String email = userRepository.findEmail(registerUserRequest.getEmail());
        if(username == null){
            if(email == null){
                if(registerUserRequest.getPassword().equals(registerUserRequest.getPasswordVerify())){
                    User user = User.builder()
                            .username(registerUserRequest.getUsername())
                            .email(registerUserRequest.getEmail())
                            .password(passwordEncoder.encode(registerUserRequest.getPassword()))
                            .isEnable(false)
                            .build();
                    Role role = roleRepository.findByName("USER_ROLE").orElseThrow(() -> new ApiNotFoundException(ROLE_NOT_FOUND));
                    user.addRole(role);
                    userRepository.save(user);
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
        String token = generateRandomUUID();
        Instant instant = Instant.now();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .expiryDate(instant.plusMillis(3600000))
                .build();
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    @Override
    public GeneralResponseModel VerifyAccount(String token) {
        Instant now = Instant.now();
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token).orElseThrow(() -> new ApiNotFoundException(TOKEN_NOT_FOUND));
        if (verificationToken.getExpiryDate().compareTo(now) >= 0) {
            String username = verificationToken.getUser().getUsername();
            User user = userRepository.findByUsernameOptional(username).orElseThrow(() -> new ApiNotFoundException(USERNAME_NOT_FOUND));
            user.setIsEnable(true);
            userRepository.save(user);
            return generalMapper.responseToGeneralResponseModel(200, "verify account", "Account activated successfully!", null, "Ok");
        }else{
            throw new ApiConflictException(TOKEN_EXPIRED);
        }
    }

    @Override
    public GeneralResponseModel login(LoginUserRequest loginUserRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserRequest.getUsername(), loginUserRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(FormatDates.instantToString(Instant.now().plusMillis(jwtProvider.getJwtExpirationTimeMillis())))
                .username(loginUserRequest.getUsername())
                .build();
        return generalMapper.responseToGeneralResponseModel(200, "login", "Logged in", Collections.singletonList(authenticationResponse), "Ok");
    }

    @Override
    public GeneralResponseModel refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        User user = userRepository.findByUsernameOptional(refreshTokenRequest.getUsername()).orElseThrow(() -> new ApiNotFoundException(USERNAME_NOT_FOUND));
        String token = jwtProvider.generateTokenWithUsername(user.getUsername());
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(FormatDates.instantToString(Instant.now().plusMillis(jwtProvider.getJwtExpirationTimeMillis())))
                .username(user.getUsername())
                .build();
        return generalMapper.responseToGeneralResponseModel(200, "refresh token", "Refresh token refreshed", Collections.singletonList(authenticationResponse), "Ok");
    }

    @Override
    public GeneralResponseModel logout(LogoutRequest logoutRequest) {
        refreshTokenService.deleteRefreshToken(logoutRequest.getRefreshToken());
        return generalMapper.responseToGeneralResponseModel(200, "logout", "Logout successfully", null, "Ok");
    }

    @Override
    public GeneralResponseModel sendEmailToResetPassword(EmailRequest emailRequest) {
        User user = userRepository.findByEmailOptional(emailRequest.getEmail()).orElseThrow(() -> new ApiNotFoundException(EMAIL_NOT_FOUND));
        String token = generateRandomUUID();
        PasswordReset passwordReset = PasswordReset.builder()
                .email(user.getEmail())
                .token(token)
                .date(Instant.now())
                .build();
        passwordResetRepository.save(passwordReset);
        mailService.setUpEmailData(MailData.SUBJECT, MailData.TITLE, user.getEmail(), MailData.BODY_RESET_PASSWORD, MailData.END_POINT_RESET_PASSWORD, token);
        return generalMapper.responseToGeneralResponseModel(200, "send email reset password", "Email was sent!", null, "Ok");
    }

    private String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}
