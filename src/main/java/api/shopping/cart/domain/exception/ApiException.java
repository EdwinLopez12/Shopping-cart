package api.shopping.cart.domain.exception;

/**
 * ApiException class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

public class ApiException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public ApiException(String message){
        super(message);
    }

    public ApiException(String message, Exception e){
        super(message, e);
    }
}
