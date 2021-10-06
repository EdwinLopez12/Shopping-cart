package api.shopping.cart.domain.usecase.impl;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * PaypalService class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - oct. 2021
 */
@Service
public class PaypalService {

//    @Value("${paypal.client.id}")
//    private String applicationClientId;
//    @Value("${paypal.client.secret}")
//    private String applicationClientSecret;

    private final String applicationClientId = "Ac6Qn4RgzT12LSnrbhC7Kadthx5WPNjNAkp6HPhkSvN-vUF8F6HeKPjPN4tZvfK5LmS8WGFqXGiAdfJZ";
    private final String applicationClientSecret = "EEvW3QIeZa2ojepPH--Jbsog8Li8-pVlCl2DMNae-Ne4Sew8EZEayHflSBBiFCMOx--Y9SmaHU7khoiH";

    /**
     *Set up the PayPal Java SDK environment with PayPal access credentials.
     *This sample uses SandboxEnvironment. In production, use LiveEnvironment.
     */
    private final PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
            applicationClientId,
            applicationClientSecret);

    /**
     *PayPal HTTP client instance with environment that has access
     *credentials context. Use to invoke PayPal APIs.
     */
    PayPalHttpClient client = new PayPalHttpClient(environment);

    /**
     *Method to get client object
     *
     *@return PayPalHttpClient client
     */
    public PayPalHttpClient client() {
        return this.client;
    }
}
