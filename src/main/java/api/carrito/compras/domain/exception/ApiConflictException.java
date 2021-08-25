package api.carrito.compras.domain.exception;

public class ApiConflictException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public ApiConflictException(String message){
        super(message);
    }
}
