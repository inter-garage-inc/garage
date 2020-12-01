package factories.order;

import garage.core.entity.order.Item;

import java.math.BigDecimal;

public class ItemFactory {
    public static Item item() {
        return Item
                .builder()
                .build();
    }

    public static Item invalidItem() {
        return Item
                .builder()
                .build();
    }
}
