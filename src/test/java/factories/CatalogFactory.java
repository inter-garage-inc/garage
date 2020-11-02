package factories;

import garage.core.entity.Catalog;

import java.math.BigDecimal;

public class CatalogFactory {
    public static Catalog catalog() {
        return Catalog
                .builder()
                .price(BigDecimal.valueOf(1000.00))
                .unit("UN")
                .description("some description")
                .build();
    }
    public static Catalog invalidCatalog() {
        return Catalog
                .builder()
                .build();
    }
}
