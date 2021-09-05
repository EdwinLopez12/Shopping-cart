package api.carrito.compras.domain.usecase;

public interface MailContentBuilder {

    String build(String title, String message, String url);
}
