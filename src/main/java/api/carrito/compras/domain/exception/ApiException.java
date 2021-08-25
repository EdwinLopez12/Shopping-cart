package api.carrito.compras.domain.exception;

public class ApiException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public ApiException(String message){
        super(message);
    }

    public ApiException(String message, Exception e){
        super(message, e);
    }
}
