package mk.netcetera.foodorder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table
public class Provider {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Provider(String name) {
        this.name = name;
    }
}
