package cat.itacademy.s04.t02.n02.fruit.fruit.model;

import cat.itacademy.s04.t02.n02.fruit.provider.model.Provider;

public record FruitResponseDTO(
        Long id,
        String name,
        int weightInKilos,
        Provider provider) {
}

