package garage.core.entity;

import garage.core.EntityBase;
import garage.core.entity.plan.Type;
import garage.core.entity.user.Status;
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
    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @NotNull
    @ManyToMany
    @JoinTable(name = "plan_catalog",
            joinColumns = {@JoinColumn(name = "plan_id")},
            inverseJoinColumns = {@JoinColumn(name = "catalog_id")})
    private List<Catalog> catalog;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Plan update(Plan attributes) {
        this.name = attributes.getName();
        this.price = attributes.getPrice();
        this.type = attributes.getType();
        this.status = attributes.getStatus();
        this.catalog = attributes.getCatalog();
        setUpdatedAt(LocalDateTime.now());
        return this;
    }
}
