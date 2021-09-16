package api.shopping.cart.domain.usecase;

import api.shopping.cart.domain.model.NotificationEmailModel;

/**
 * MailService interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

public interface MailService {

    void setUpEmailData(String subject, String title, String email, String body, String endPoint, String token);
    void sendEmail(NotificationEmailModel notificationEmailModel);
}
