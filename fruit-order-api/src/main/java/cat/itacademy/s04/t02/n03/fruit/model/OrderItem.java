package cat.itacademy.s04.t02.n03.fruit.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @NotBlank(message = "Name can't be blank") String fruitName;
    @Positive(message = "weight must be a positive value") int quantityInKilos;
}
