package garage.core.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import garage.core.EntityBase;
import garage.core.entity.Catalog;
import garage.core.entity.Order;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "items")
@EqualsAndHashCode(callSuper = false)
public class Item extends EntityBase {
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    @NotNull
    @Column(nullable = false)
    private BigDecimal amount;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;
}
