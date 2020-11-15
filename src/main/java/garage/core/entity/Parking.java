package garage.core.entity;

import garage.core.EntityBase;
import garage.core.entity.parking.ParkingSpace;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "parkings")
@EqualsAndHashCode(callSuper = false)
public class Parking extends EntityBase {

    private LocalDateTime checkInAt;

    private LocalDateTime checkoutAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_space_id")
    private ParkingSpace parkingSpace;
}