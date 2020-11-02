package garage.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import garage.core.EntityBase;
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
    @Column(nullable = false)
    private String neighborhood;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @NotNull
    @Column(nullable = false)
    private String country;

    @NotNull
    @Column(name = "postal_code")
    private Integer postalCode;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
