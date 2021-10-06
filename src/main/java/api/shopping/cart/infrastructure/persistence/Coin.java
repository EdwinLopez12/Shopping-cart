package api.shopping.cart.infrastructure.persistence;

/**
 * Coin enum
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - oct. 2021
 */
public enum Coin {

    EUR("Euro Member Countries"),
    USD("United States Dollar"),
    COP("Colombian Peso");

    private final String description;

    Coin(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
