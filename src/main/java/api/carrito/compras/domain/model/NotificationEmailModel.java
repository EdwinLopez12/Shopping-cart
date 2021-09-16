package api.carrito.compras.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * NotificationEmailModel class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
public class NotificationEmailModel {

    @JsonProperty
    private String subject;

    @JsonProperty
    private String title;

    @JsonProperty
    private String recipient;

    @JsonProperty
    private String body;

    @JsonProperty
    private String url;
}
