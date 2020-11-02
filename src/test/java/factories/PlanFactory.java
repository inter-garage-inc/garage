package factories;

import garage.core.entity.Plan;

public class PlanFactory {
    public static Plan plan() {
        return Plan
                .builder()
                .description("some description")
                .build();
    }

    public static Plan invalidPlan() {
        return Plan
                .builder()
                .build();
    }
}
