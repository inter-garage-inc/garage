package garage.core.entity;

import garage.core.EntityBase;
import garage.core.entity.parking.ParkingSpace;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "parkings")
@EqualsAndHashCode(callSuper = false)
public class Parking extends EntityBase {

    @NotNull
    @Column(nullable = false)
    private Integer number;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_space_id")
    private ParkingSpace parkingSpace;
}
