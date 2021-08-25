package api.carrito.compras.infrastructure;

/**
 * The Routes mapping.
 * Clase para el mapeo de las rutas de toda la aplicación
 */
public final class RoutesMapping {
    private RoutesMapping(){}

    public static final String FULL_BASE_V1 = "http://localhost:9090";
    public static final String URL_AUTH_V1 = "/api/v1/auth";
    public static final String URL_CUSTOMERS_V1 = "/api/v1/customers";
    public static final String URL_HOUSES_V1 = "/api/v1/houses";
    public static final String URL_PRODUCTS_V1 = "/api/v1/products";
    public static final String URL_COMPANY_INFORMATION_V1 = "/api/v1/company-information";
    public static final String URL_INVOICES_V1 = "/api/v1/invoices";
    public static final String URL_PAYMENTS_V1 = "/api/v1/payments";
}
