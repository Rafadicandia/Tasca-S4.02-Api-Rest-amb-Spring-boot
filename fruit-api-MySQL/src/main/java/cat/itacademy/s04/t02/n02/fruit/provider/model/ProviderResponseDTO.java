package cat.itacademy.s04.t02.n02.fruit.provider.model;

import cat.itacademy.s04.t02.n02.fruit.fruit.model.FruitResponseDTO;

import java.util.List;

public record ProviderResponseDTO(
        Long id,
        String name,
        String country,
        List<FruitResponseDTO> fruits) {
}
