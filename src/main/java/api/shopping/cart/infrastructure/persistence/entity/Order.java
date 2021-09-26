package api.shopping.cart.infrastructure.persistence.entity;

import api.shopping.cart.infrastructure.persistence.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * Order class
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
@Table(name = "tb_order")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OrderStatus status;

    private String date;

    private BigDecimal totalPayment;

    @OneToMany(targetEntity = OrderProduct.class, fetch = FetchType.LAZY)
    private List<OrderProduct> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_data_id")
    private UserData userData;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;
}
