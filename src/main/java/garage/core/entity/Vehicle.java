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
    @Column(name = "license_plate")
    private String licencePlate;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Vehicle update(Vehicle attributes) {
        this.licencePlate = attributes.getLicencePlate();
        return this;
    }
}
