package api.shopping.cart.domain.dto.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * CategoryRequest class
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
public class CategoryRequest {

    @JsonProperty
    @NotBlank(message = "Product name is required")
    @Length(min = 3, max = 255, message = "Product name should be greater than or equal to 3")
    private String name;

    @JsonProperty
    @NotBlank(message = "Description is required")
    @Length(min = 3, max = 255, message = "Description should be greater than or equal to 5")
    private String description;
}
