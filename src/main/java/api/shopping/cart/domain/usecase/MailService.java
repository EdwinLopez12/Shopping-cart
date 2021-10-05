package api.shopping.cart.domain.usecase;

import org.springframework.scheduling.annotation.Async;

/**
 * MailService interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

public interface MailService {

    @Async
    void setUpEmailData(String template, String subject, String title, String email, String message, String button, String endPoint, String token);
}
