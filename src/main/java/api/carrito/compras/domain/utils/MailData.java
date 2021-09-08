package api.carrito.compras.domain.utils;

public final class MailData {

    private MailData() {}

    public static final String SUBJECT ="Shopping Cart";
    public static final String TITLE = "Hi! ";
    public static final String BODY_SIGNUP = "Thanks for signing up. Click on the link to activate the account.";
    public static final String BODY_RESET_PASSWORD = "You are reading this message because we received a request to change your password from your account. If you didn't, no further action is required, but if you did, please click on the following URL to reset your password";
    public static final String END_POINT_SIGNUP = "/account-verification";
    public static final String END_POINT_RESET_PASSWORD = "/reset/password";
}
