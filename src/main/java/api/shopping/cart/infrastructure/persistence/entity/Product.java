package api.shopping.cart.infrastructure.persistence.entity;

import api.shopping.cart.infrastructure.persistence.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Product class
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
@Table(name = "tb_product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    private String description;

    private Double weight;

    private Integer total;

    private ProductStatus productStatus;

    private BigDecimal price;

    @ManyToMany(targetEntity = Category.class)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    /**
     * Add category.
     *
     * @param category the category
     */
    public void addCategory(Category category) {
        if (categories == null) {
            categories = new ArrayList<>(Collections.singletonList(category));
        }else{
            categories.add(category);
        }
    }

    /**
     * Remove category.
     *
     * @param category the category
     */
    public void removeCategory(Category category) {
        if (categories != null) {
            categories.remove(category);
        }
    }
}
