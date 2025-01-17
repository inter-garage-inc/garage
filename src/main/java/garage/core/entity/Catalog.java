package garage.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import garage.core.EntityBase;
import garage.core.entity.catalog.Status;
import garage.core.entity.catalog.Type;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    public Catalog update(Catalog attributes) {
        this.description = attributes.getDescription();
        this.price = attributes.getPrice();
        this.status = attributes.getStatus();
        setUpdatedAt(LocalDateTime.now());
        return this;
    }
}

