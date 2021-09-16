package api.shopping.cart.domain.exception;

/**
 * ApiConflictException class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */


public class ApiConflictException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public ApiConflictException(String message){
        super(message);
    }
}
