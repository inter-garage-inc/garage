package garage.core.entity.parking;

import garage.core.EntityBase;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "parking_spaces")
@EqualsAndHashCode(callSuper = false)
public class ParkingSpace extends EntityBase {
    @NotNull
    @Column(nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SpaceStatus status;

    public ParkingSpace update(ParkingSpace attributes) {
        this.code = attributes.getCode();
        this.status = attributes.getStatus();
        setUpdatedAt(LocalDateTime.now());
        return this;
    }
}
