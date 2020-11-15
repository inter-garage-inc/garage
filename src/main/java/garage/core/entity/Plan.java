package garage.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import garage.core.EntityBase;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "plans")
@EqualsAndHashCode(callSuper = false)
public class Plan extends EntityBase {

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @ManyToMany
    @JoinTable(name = "plan_catalog",
            joinColumns = {@JoinColumn(name = "plan_id")},
            inverseJoinColumns = {@JoinColumn(name = "catalog_id")})
    private List<Catalog> catalog;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;
}
