package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.exception.ApiException;
import api.carrito.compras.domain.model.NotificationEmailModel;
import api.carrito.compras.domain.usecase.MailContentBuilder;
import api.carrito.compras.domain.usecase.MailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static api.carrito.compras.infrastructure.RoutesMapping.FULL_BASE_V1;
import static api.carrito.compras.infrastructure.RoutesMapping.URL_AUTH_V1;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private static final String MAIL_EXCEPTION = "An exception occurred while sending an email to ";

    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;

    @Override
    @Async
    public void setUpEmailData(String subject, String title, String email, String body, String endPoint, String token) {
        NotificationEmailModel notificationEmailModel = NotificationEmailModel.builder()
                .subject(subject)
                .title(title)
                .recipient(email)
                .body(body)
                .url(FULL_BASE_V1 + URL_AUTH_V1 + endPoint+"/"+token)
                .build();
        sendEmail(notificationEmailModel);
    }

    @Override
    public void sendEmail(NotificationEmailModel notificationEmailModel) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("carritocompras@mail.com");
            messageHelper.setTo(notificationEmailModel.getRecipient());
            messageHelper.setSubject(notificationEmailModel.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmailModel.getTitle(), notificationEmailModel.getBody(), notificationEmailModel.getUrl()));
        };
        try{
            javaMailSender.send(mimeMessagePreparator);
        }catch (MailException mailException){
            throw new ApiException(MAIL_EXCEPTION + notificationEmailModel.getRecipient(), mailException);
        }
    }
}
