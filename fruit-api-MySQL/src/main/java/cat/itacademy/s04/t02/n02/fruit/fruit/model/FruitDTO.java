package cat.itacademy.s04.t02.n02.fruit.fruit.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record FruitDTO(
        @NotBlank(message = "Name can't be blank") String name,
        @Positive(message = "Weight must be positive") int weightInKilos,
        @NotNull(message = "Provider ID is required") Long providerId) {

}
