package api.shopping.cart.domain.usecase.impl;

import api.shopping.cart.domain.exception.ApiException;
import api.shopping.cart.domain.model.NotificationEmailModel;
import api.shopping.cart.domain.usecase.MailContentBuilder;
import api.shopping.cart.domain.usecase.MailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static api.shopping.cart.infrastructure.RoutesMapping.FULL_BASE_HEROKU;
import static api.shopping.cart.infrastructure.RoutesMapping.FULL_BASE_V1;
import static api.shopping.cart.infrastructure.RoutesMapping.URL_AUTH_V1;

/**
 * MailServiceImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private static final String MAIL_EXCEPTION = "An exception occurred while sending an email to ";

    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;

    @Override
    @Async
    public void setUpEmailData(String template, String subject, String title, String email, String message, String button, String endPoint, String token) {
        NotificationEmailModel notificationEmailModel = NotificationEmailModel.builder()
                .subject(subject)
                .title(title)
                .recipient(email)
                .message(message)
                .button(button)
//                .url(String.format("%s%s/%s", FULL_BASE_V1 + URL_AUTH_V1, endPoint, token))
                .url(String.format("%s%s/%s", FULL_BASE_HEROKU + URL_AUTH_V1, endPoint, token))
                .build();
        sendEmail(notificationEmailModel, template);
    }

    public void sendEmail(NotificationEmailModel notificationEmailModel, String template) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("authenticationapi0@gmail.com");
            messageHelper.setTo(notificationEmailModel.getRecipient());
            messageHelper.setSubject(notificationEmailModel.getSubject());
            messageHelper.setText(
                    mailContentBuilder.build(
                            template,
                            notificationEmailModel.getTitle(),
                            notificationEmailModel.getMessage(),
                            notificationEmailModel.getButton(),
                            notificationEmailModel.getUrl()
                    ), true);
        };
        try{
            javaMailSender.send(mimeMessagePreparator);
        }catch (MailException mailException){
            throw new ApiException(MAIL_EXCEPTION + notificationEmailModel.getRecipient(), mailException);
        }
    }
}
