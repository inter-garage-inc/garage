package garage.core.entity;

import garage.core.EntityBase;
import garage.core.entity.plan.Type;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "plans")
@EqualsAndHashCode(callSuper = false)
public class Plan extends EntityBase {

    @NotNull
    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    private String name;

    @NotNull
    @ManyToMany
    @JoinTable(name = "plan_catalog",
            joinColumns = {@JoinColumn(name = "plan_id")},
            inverseJoinColumns = {@JoinColumn(name = "catalog_id")})
    private List<Catalog> catalog;

    @NotNull
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "type", nullable = false)
    private Type type;

    @NotNull
    @Column(name = "status",  nullable = false)
    private Status status;

    public Plan update(Plan attributes) {
        this.name = attributes.getName();
        this.price = attributes.getPrice();
        this.type = attributes.getType();
        this.status = attributes.getStatus();
        setUpdatedAt(LocalDateTime.now());
        return this;
    }
}
