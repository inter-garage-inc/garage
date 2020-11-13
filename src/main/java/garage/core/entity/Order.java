package garage.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import garage.core.EntityBase;
import garage.core.entity.order.Item;
import garage.core.entity.order.PaymentMethod;
import garage.core.entity.order.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@EqualsAndHashCode(callSuper = false)
public class Order extends EntityBase {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<Item> items;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @NotNull
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmout;

    @NotNull
    @Column(name = "status", nullable = false)
    private Status status;

    public Order update(Order attributes) {
        this.status = attributes.getStatus();
        this.totalAmout = attributes.getTotalAmout();
        return this;
    }
}
