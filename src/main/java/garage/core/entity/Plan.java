package garage.core.entity;

import garage.core.EntityBase;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "plans")
@EqualsAndHashCode(callSuper = false)
public class Plan extends EntityBase {
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;

    @NotNull
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;
}
