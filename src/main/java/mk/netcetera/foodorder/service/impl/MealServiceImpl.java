package mk.netcetera.foodorder.service.impl;

import lombok.RequiredArgsConstructor;
import mk.netcetera.foodorder.model.Meal;
import mk.netcetera.foodorder.model.MealType;
import mk.netcetera.foodorder.model.Provider;
import mk.netcetera.foodorder.model.exceptions.InvalidMealIdException;
import mk.netcetera.foodorder.repository.MealRepository;
import mk.netcetera.foodorder.service.MealService;
import mk.netcetera.foodorder.service.ProviderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static mk.netcetera.foodorder.service.FieldFilterSpecification.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private final ProviderService providerService;

    @Override
    public List<Meal> listAll() {
        return mealRepository.findAll();
    }

    @Override
    public Meal findById(Long id) {
        Optional<Meal> res = mealRepository.findById(id);
        if (res.isPresent()) {
            return res.get();
        } else {
            throw new InvalidMealIdException();
        }
    }

    @Override
    public Meal create(String name, String description, LocalDate availableDate, Integer portions, MealType mealType, Long providerId) {
        Provider provider = providerService.findById(providerId);
        Meal meal = new Meal(name, description, availableDate, portions, mealType, provider);
        mealRepository.save(meal);
        return meal;
    }

    @Override
    public Meal update(Long id, String name, String description, LocalDate availableDate, Integer portions, MealType mealType, Long providerId) {
        Meal meal = findById(id);
        Provider provider = providerService.findById(providerId);
        meal.setName(name);
        meal.setDescription(description);
        meal.setAvailableDate(availableDate);
        meal.setPortions(portions);
        meal.setMealType(mealType);
        meal.setProvider(provider);
        mealRepository.save(meal);
        return meal;
    }

    @Override
    public Meal delete(Long id) {
        Meal meal = findById(id);
        mealRepository.delete(meal);
        return meal;
    }

    @Override
    public Meal order(Long id) {
        Meal meal = findById(id);
        if (meal.getPortions() == null || meal.getOrderCount() == null || meal.getOrderCount() < meal.getPortions()) {
            meal.setOrderCount((meal.getOrderCount() == null ? 0 : meal.getOrderCount()) + 1);
            mealRepository.save(meal);
        }
        return meal;
    }

    @Override
    public Page<Meal> findPage(String name, MealType mealType, Long provider, int pageNum, int pageSize) {
        Specification<Meal> specification = Specification.allOf(
                filterContainsText(Meal.class, "name", name),
                filterEquals(Meal.class, "provider.id", provider),
                filterEqualsV(Meal.class, "mealType", mealType)
        );

        return mealRepository.findAll(
                specification,
                PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "name")));
    }
}
