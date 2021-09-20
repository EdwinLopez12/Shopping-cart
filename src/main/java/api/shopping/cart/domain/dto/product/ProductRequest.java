package api.shopping.cart.domain.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * ProductRequest class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequest {

    @JsonProperty
    @NotBlank(message = "Code is required")
    @Length(min = 3, max = 255, message = "Code should be greater than or equal to 3")
    private String code;

    @JsonProperty
    @NotBlank(message = "Product name is required")
    @Length(min = 3, max = 255, message = "Product name should be greater than or equal to 3")
    private String name;

    @JsonProperty
    @NotBlank(message = "Description is required")
    @Length(min = 3, max = 255, message = "Description should be greater than or equal to 5")
    private String description;

    @JsonProperty
    @NotNull(message = "Weight is required")
    private Double weight;

    @JsonProperty
    @NotNull(message = "Total is required")
    private Integer total;

    @JsonProperty
    @NotNull(message = "Price is required")
    private BigDecimal price;

    @JsonProperty
    @NotNull(message = "Category is required")
    private Long category;
}
