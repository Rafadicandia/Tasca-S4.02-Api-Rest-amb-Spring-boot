package cat.itacademy.s04.t02.n02.fruit.provider.model;

import jakarta.validation.constraints.NotBlank;

public record ProviderDTO(
        @NotBlank(message= "name can't be blank")
        String name,
        @NotBlank(message = "The country cannot be empty")
        String country

) {
}
