package api.carrito.compras.domain.exception;

public class ApiNotFoundException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public ApiNotFoundException(String message){
        super(message);
    }
}
