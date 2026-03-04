package mk.netcetera.foodorder.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table
public class Meal {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private LocalDate availableDate;

    private Integer portions;

    private Integer orderCount = 0;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @ManyToOne
    private Provider provider;

    public Meal(String name,
                String description,
                LocalDate availableDate,
                Integer portions,
                MealType mealType,
                Provider provider) {
        this.name = name;
        this.description = description;
        this.availableDate = availableDate;
        this.portions = portions;
        this.orderCount = 0;
        this.mealType = mealType;
        this.provider = provider;
    }
}
