package api.shopping.cart.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Link class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Link {

    @JsonProperty
    private String rel;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HttpMethods type = HttpMethods.GET;

    @JsonProperty
    private String href;

    public enum HttpMethods {
        GET,
        POST,
        PUT,
        PATCH,
        DELETE
    }
}