package api.shopping.cart.domain.exception;

/**
 * ApiNotFoundException class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */


public class ApiNotFoundException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public ApiNotFoundException(String message){
        super(message);
    }
}
