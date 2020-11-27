package garage.core.entity;

import garage.core.EntityBase;
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
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "status",  nullable = false)
    private Status status;

    public Plan update(Plan attributes) {
        this.description = attributes.getDescription();
        this.price = attributes.getPrice();
        this.status = attributes.getStatus();
        setUpdatedAt(LocalDateTime.now());
        return this;
    }
}
