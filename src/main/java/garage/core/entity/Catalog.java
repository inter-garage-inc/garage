package garage.core.entity;

import garage.core.EntityBase;
import garage.core.entity.catalog.Status;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "catalogs")
@EqualsAndHashCode(callSuper = false)
public class Catalog extends EntityBase {
    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(nullable = false)
    private Status status;

    public Catalog update(Catalog attributes) {
        this.description = attributes.getDescription();
        this.price = attributes.getPrice();
        this.status = attributes.getStatus();
        return this;
    }
}

