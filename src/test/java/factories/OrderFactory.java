package factories;

import garage.core.entity.Order;
import garage.core.entity.order.Status;

import java.math.BigDecimal;

public class OrderFactory {
    public static Order order() {
        return Order.builder()
                .status(Status.PAID)
                .totalAmout(BigDecimal.valueOf(1000))
                .build();
    }

    public static Order invalidOrder() {
        return Order.builder()
                .build();
    }
}
