package api.carrito.compras.domain.utils;

public final class MailData {

    private MailData() {}

    public static final String SUBJECT ="Carrito compras";
    public static final String TITLE = "¡Hola! ";
    public static final String BODY_SIGNUP = "Gracias por registrarte. Da click en el enlace para activar la cuenta.";
    public static final String BODY_RESET_PASSWORD = "Estas leyendo este mensaje porque recibimos una solucitud de cambio de contraseña desde tu cuenta. Si no lo hiciste no se requieren más acciones, pero si lo hiciste, por favor has click en la siguiente URL para restablecer su contraseña";
    public static final String END_POINT_SIGNUP = "/account-verification";
    public static final String END_POINT_RESET_PASSWORD = "/reset/password";
}
