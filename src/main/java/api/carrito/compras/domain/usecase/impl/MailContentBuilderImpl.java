package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.usecase.MailContentBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentBuilderImpl implements MailContentBuilder {

    private final TemplateEngine templateEngine;

    @Override
    public String build(String title, String message, String url) {
        Context context = new Context();
        context.setVariable("title", title);
        context.setVariable("message", message);
        context.setVariable("url", url);
        return templateEngine.process("mailTemplate", context);
    }
}
