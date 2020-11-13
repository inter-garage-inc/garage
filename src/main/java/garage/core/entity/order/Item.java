package garage.core.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import garage.core.EntityBase;
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
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull
    @Column
    private BigDecimal amout;
}
