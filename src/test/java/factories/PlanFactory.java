package factories;

import garage.core.entity.Plan;
import garage.core.entity.Status;

import java.math.BigDecimal;

public class PlanFactory {
    public static Plan plan() {
        return Plan
                .builder()
                .description("some description")
                .price(BigDecimal.valueOf(10.00))
                .status(Status.ACTIVE)
                .build();
    }

    public static Plan invalidPlan() {
        return Plan
                .builder()
                .build();
    }
}
