package api.carrito.compras.domain.exception;

import api.carrito.compras.domain.model.ExceptionMethodArgumentNotValidModel;
import api.carrito.compras.domain.model.ExceptionResponseModel;
import api.carrito.compras.domain.model.GeneralResponseModel;
import api.carrito.compras.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@AllArgsConstructor
public class ExceptionHandlerApi {

    private static final String STATUS = "Error";
    private final GeneralResponseModelMapper generalResponseModelMapper;

    /**
     * Global Exception Handler
     *
     * @param request   the request
     * @param exception the exception
     * @return the exception handler response
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GeneralResponseModel handleGlobalException(HttpServletRequest request, Throwable exception) {
        return generalResponseModelMapper.responseToGeneralExceptionResponseModel(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getLocalizedMessage(), request.getServletPath());
    }

    /**
     * Authentication Exception Handler
     *
     * @param request   the request
     * @param exception the exception
     * @return the exception handler response
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GeneralResponseModel handleAuthenticationException(HttpServletRequest request, Throwable exception){
        return generalResponseModelMapper.responseToGeneralExceptionResponseModel(STATUS, HttpStatus.UNAUTHORIZED.value(), "Authentication required to access the site", request.getServletPath());
    }

    /**
     * Denied Exception Handler
     *
     * @param request the request
     * @return the exception handler response
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GeneralResponseModel handleAccessDeniedException(HttpServletRequest request, AccessDeniedException exception) {
        return generalResponseModelMapper.responseToGeneralExceptionResponseModel(STATUS, HttpStatus.UNAUTHORIZED.value(), exception.getLocalizedMessage(), request.getServletPath());

    }

    /**
     * Conflict Exception Handler
     *
     * @param request   the request
     * @param exception the exception
     * @return the exception handler response
     */
    @ExceptionHandler(ApiConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public GeneralResponseModel handleConflictException(HttpServletRequest request, Throwable exception){
        return generalResponseModelMapper.responseToGeneralExceptionResponseModel(STATUS, HttpStatus.CONFLICT.value(), exception.getLocalizedMessage(), request.getServletPath());
    }

    /**
     * Not Found Exception Handler
     *
     * @param request   the request
     * @param exception the exception
     * @return the exception handler response
     */
    @ExceptionHandler(ApiNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GeneralResponseModel handleNoFoundException(HttpServletRequest request, Throwable exception){
        return generalResponseModelMapper.responseToGeneralExceptionResponseModel(STATUS, HttpStatus.NOT_FOUND.value(), exception.getLocalizedMessage(), request.getServletPath());
    }

    /**
     * Arg no valid Exception Handler
     *
     * @param request   the request
     * @param exception the exception
     * @return the exception handler response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public GeneralResponseModel handleUnprocessableEntity(HttpServletRequest request, MethodArgumentNotValidException exception){
        ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            ExceptionMethodArgumentNotValidModel errorModel = ExceptionMethodArgumentNotValidModel.builder()
                    .fieldName(((FieldError) error).getField())
                    .rejectedValue(((FieldError) error).getRejectedValue())
                    .errorMessage(error.getDefaultMessage())
                    .errorType(error.getCode())
                    .build();
            exceptionResponseModel.getErrors().add(errorModel);
        }
        return generalResponseModelMapper.responseToInvalidMethodArgumentExceptionResponseModel(STATUS, HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation failed", request.getServletPath(), exceptionResponseModel);
    }
}
