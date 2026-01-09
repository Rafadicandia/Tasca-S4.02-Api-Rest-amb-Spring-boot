package cat.itacademy.s04.s02.n01.fruit.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public record FruitDTO(
        @NotBlank(message = "Name can't be blank") String name,
        @Positive(message = "Weight must be positive") int weightInKilos) {

}
