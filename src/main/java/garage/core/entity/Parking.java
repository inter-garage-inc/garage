package garage.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name = "check_in_at")
    private LocalDateTime checkInAt;

    @Column(name = "checkout_at")
    private LocalDateTime checkoutAt;

    @JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_space_id")
    private ParkingSpace parkingSpace;
}