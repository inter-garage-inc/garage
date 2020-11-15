package garage.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import garage.core.EntityBase;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "vehicles")
@EqualsAndHashCode(callSuper = false)
public class Vehicle extends EntityBase {

    @NotNull
    @Column(name = "license_plate", nullable = false)
    private String licencePlate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Vehicle update(Vehicle attributes) {
        this.licencePlate = attributes.getLicencePlate();
        return this;
    }
}
