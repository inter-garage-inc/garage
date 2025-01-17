package factories;

import garage.core.entity.Catalog;
import garage.core.entity.catalog.Status;
import garage.core.entity.catalog.Type;
import java.math.BigDecimal;

public class CatalogFactory {
    public static Catalog catalog() {
        return Catalog
                .builder()
                .price(BigDecimal.valueOf(1000.00))
                .description("some description")
                .status(Status.AVAILABLE)
                .type(Type.OTHER)
                .build();
    }
    public static Catalog invalidCatalog() {
        return Catalog
                .builder()
                .build();
    }
}
