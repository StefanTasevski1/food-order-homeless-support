package mk.netcetera.foodorder.web;

import lombok.RequiredArgsConstructor;
import mk.netcetera.foodorder.model.MealType;
import mk.netcetera.foodorder.service.MealService;
import mk.netcetera.foodorder.service.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;
    private final ProviderService providerService;

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }

    @GetMapping(path = {"/", "/meals"})
    public String listAll(@RequestParam(required = false) String name,
                          @RequestParam(required = false) MealType mealType,
                          @RequestParam(required = false) Long provider,
                          @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                          Model model) {
        model.addAttribute("meals", mealService.findPage(name, mealType, provider, pageNum - 1, pageSize));
        model.addAttribute("providers", providerService.listAll());
        model.addAttribute("mealTypes", MealType.values());
        model.addAttribute("filterName", name != null ? name : "");
        model.addAttribute("filterMealType", mealType != null ? mealType.name() : "");
        model.addAttribute("filterProvider", provider != null ? String.valueOf(provider) : "");
        return "list";
    }

    @GetMapping("/meals/add")
    public String showAdd(Model model) {
        model.addAttribute("providers", providerService.listAll());
        model.addAttribute("mealTypes", MealType.values());
        return "form";
    }

    @GetMapping("/meals/edit/{id}")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("providers", providerService.listAll());
        model.addAttribute("mealTypes", MealType.values());
        model.addAttribute("meal", mealService.findById(id));
        return "form";
    }

    @PostMapping("/meals")
    public String create(@RequestParam String name,
                         @RequestParam String description,
                         @RequestParam LocalDate availableDate,
                         @RequestParam Integer portions,
                         @RequestParam MealType mealType,
                         @RequestParam Long provider,
                         RedirectAttributes redirectAttributes) {
        mealService.create(name, description, availableDate, portions, mealType, provider);
        redirectAttributes.addFlashAttribute("message", "Meal created.");
        return "redirect:/meals";
    }

    @PostMapping("/meals/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String description,
                         @RequestParam LocalDate availableDate,
                         @RequestParam Integer portions,
                         @RequestParam MealType mealType,
                         @RequestParam Long provider,
                         RedirectAttributes redirectAttributes) {
        mealService.update(id, name, description, availableDate, portions, mealType, provider);
        redirectAttributes.addFlashAttribute("message", "Meal updated.");
        return "redirect:/meals";
    }

    @PostMapping("/meals/delete/{id}")
    public String delete(@PathVariable Long id) {
        mealService.delete(id);
        return "redirect:/meals";
    }

    @PostMapping("/meals/order/{id}")
    public String order(@PathVariable Long id) {
        mealService.order(id);
        return "redirect:/meals";
    }
}
