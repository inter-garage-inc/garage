package garage.core.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import garage.core.EntityBase;
import garage.core.entity.Catalog;
import garage.core.entity.Order;
import garage.core.entity.Parking;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "items")
@EqualsAndHashCode(callSuper = false)
public class Item extends EntityBase {
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(nullable = false)
    private String description;

    @JsonIgnore
    @JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    @JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_id")
    private Parking parking;

    public static List<Item> listOf(List<Catalog> catalogs) {
        return catalogs
                .stream()
                .map(Item::of)
                .collect(Collectors.toList());
    }

    public static Item of(Catalog catalog) {
        return Item
                .builder()
                .catalog(catalog)
                .price(catalog.getPrice())
                .description(catalog.getDescription())
                .build();
    }
}
