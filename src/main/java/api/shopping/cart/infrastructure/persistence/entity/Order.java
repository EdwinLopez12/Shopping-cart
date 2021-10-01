package api.shopping.cart.infrastructure.persistence.entity;

import api.shopping.cart.infrastructure.persistence.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
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
import java.util.ArrayList;
import java.util.Collections;
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

    private BigDecimal totalPayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_data_id")
    private UserData userData;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = {CascadeType.MERGE})
    private List<OrderProduct> orderProducts;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    /**
     * Add product.
     *
     * @param orderProduct the order product
     */
    public void addOrderProduct(OrderProduct orderProduct) {
        boolean b = false;
        if (orderProducts == null) {
            orderProducts = new ArrayList<>(Collections.singletonList(orderProduct));
        }else{
            for (OrderProduct op : orderProducts) {
                if (op.getProduct().getId().equals(orderProduct.getProduct().getId())) {
                    op.setAmount(op.getAmount() + orderProduct.getAmount());
                    b=true;
                }
            }
            if (!b) orderProducts.add(orderProduct);
        }
    }

    /**
     * Remove order product.
     *
     * @param orderProduct the order product
     */
    public void removeOrderProduct(OrderProduct orderProduct) {
        if (orderProducts != null) {
            for (int i = orderProducts.size() - 1; i>=0; i--) {
                if (orderProducts.get(i).getProduct().getId().equals(orderProduct.getProduct().getId())) orderProducts.remove(i);
            }
        }
    }
}

