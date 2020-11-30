package factories;

import garage.core.entity.Plan;
import garage.core.entity.Status;
import garage.core.entity.plan.Type;

import java.math.BigDecimal;
import java.util.List;

public class PlanFactory {
    public static Plan plan() {
        return Plan
                .builder()
                .name("name")
                .type(Type.MONTHLY)
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
