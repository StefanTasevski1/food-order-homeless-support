package mk.netcetera.foodorder.service;

import mk.netcetera.foodorder.model.Meal;
import mk.netcetera.foodorder.model.MealType;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface MealService {

    List<Meal> listAll();

    Meal findById(Long id);

    Meal create(String name, String description, LocalDate availableDate, Integer portions, MealType mealType, Long providerId);

    Meal update(Long id, String name, String description, LocalDate availableDate, Integer portions, MealType mealType, Long providerId);

    Meal delete(Long id);

    Meal order(Long id);

    Page<Meal> findPage(String name, MealType mealType, Long provider, int pageNum, int pageSize);
}
