package garage.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String licensePlate;

    @JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    public Vehicle update(Vehicle attributes) {
        this.licensePlate = attributes.getLicensePlate();
        this.plan = attributes.getPlan();
        return this;
    }
}