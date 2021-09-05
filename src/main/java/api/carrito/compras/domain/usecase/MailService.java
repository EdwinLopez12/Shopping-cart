package api.carrito.compras.domain.usecase;

import api.carrito.compras.domain.model.NotificationEmailModel;

public interface MailService {

    void setUpEmailData(String subject, String title, String email, String body, String endPoint, String token);
    void sendEmail(NotificationEmailModel notificationEmailModel);
}
