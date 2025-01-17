package factories;

import garage.core.entity.Order;
import garage.core.entity.order.Status;
import java.math.BigDecimal;

public class OrderFactory {
    public static Order order() {
        return Order.builder()
                .status(Status.OPEN)
                .licensePlate("XXX-0000")
                .build();
    }

    public static Order invalidOrder() {
        return Order.builder()
                .build();
    }
}
