package api.shopping.cart.infrastructure.persistence;

/**
 * OrderStatus class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public enum OrderStatus {
    PAID,
    PROCESSING,
    PENDING,
    CANCELLED,
    REJECTED
}
