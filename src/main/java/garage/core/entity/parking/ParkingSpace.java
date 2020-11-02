package garage.core.entity.parking;

import garage.core.EntityBase;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "parking_spaces")
@EqualsAndHashCode(callSuper = false)
public class ParkingSpace extends EntityBase {
    @NotNull
    @Column
    private Integer number;
}
