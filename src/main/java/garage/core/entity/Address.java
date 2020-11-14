package garage.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import garage.core.EntityBase;
import garage.core.entity.address.Country;
import garage.core.entity.address.State;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
@EqualsAndHashCode(callSuper = false)
public class Address extends EntityBase {
    @NotNull
    @Column(nullable = false)
    private String street;

    @Column
    private String number;

    @Column
    private String complement;

    @NotNull
    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Country country;
}
