package api.carrito.compras.domain.usecase;

/**
 * MailContentBuilder interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

public interface MailContentBuilder {

    String build(String title, String message, String url);
}
