package cat.itacademy.s04.s02.n01.fruit.services;

import cat.itacademy.s04.s02.n01.fruit.model.FruitDTO;
import cat.itacademy.s04.s02.n01.fruit.model.FruitResponseDTO;

public interface FruitService {
    public FruitResponseDTO save(FruitDTO userRequestNewFruit);
}
