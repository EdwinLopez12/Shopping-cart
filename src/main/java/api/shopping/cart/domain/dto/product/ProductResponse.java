package api.shopping.cart.domain.dto.product;

import api.shopping.cart.infrastructure.persistence.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * ProductResponse class
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
public class ProductResponse {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Double weight;
    private Integer total;
    private BigDecimal price;
    private ProductStatus status;
}
