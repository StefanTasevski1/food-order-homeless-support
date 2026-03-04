package mk.netcetera.foodorder.config;

import jakarta.annotation.PostConstruct;
import mk.netcetera.foodorder.model.MealType;
import mk.netcetera.foodorder.service.MealService;
import mk.netcetera.foodorder.service.ProviderService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer {

    private final ProviderService providerService;
    private final MealService mealService;

    public DataInitializer(ProviderService providerService, MealService mealService) {
        this.providerService = providerService;
        this.mealService = mealService;
    }

    private MealType randomize(int i) {
        MealType[] types = MealType.values();
        return types[i % types.length];
    }

    @PostConstruct
    public void initData() {
        for (int i = 1; i < 6; i++) {
            this.providerService.create("Soup Kitchen " + i);
        }

        for (int i = 1; i < 11; i++) {
            this.mealService.create(
                    "Meal " + i,
                    "Description for meal " + i,
                    LocalDate.now().plusDays(i),
                    30,
                    this.randomize(i),
                    this.providerService.listAll().get((i - 1) % 5).getId()
            );
        }
    }
}
