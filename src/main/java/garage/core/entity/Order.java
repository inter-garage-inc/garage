package garage.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<Item> items;

    @JsonIgnore
    @JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "license_plate", nullable = false)
    private String licensePlate;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @NotNull
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public Order update(Order attributes) {
        this.paymentMethod = attributes.getPaymentMethod();
        this.totalAmount = attributes.getTotalAmount();
        this.status = attributes.getStatus();
        return this;
    }
}
