package cat.itacademy.s04.s02.n01.fruit.services;

import cat.itacademy.s04.s02.n01.fruit.model.Fruit;
import cat.itacademy.s04.s02.n01.fruit.model.FruitDTO;
import cat.itacademy.s04.s02.n01.fruit.model.FruitResponseDTO;
import cat.itacademy.s04.s02.n01.fruit.repository.FruitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@AllArgsConstructor
@Service
public class FruitServiceImpl implements FruitService {

    private final FruitRepository repository;

    @Override
    public FruitResponseDTO save(FruitDTO userRequestNewFruit) {
        Fruit fruitEntity = new Fruit();
        fruitEntity.setName(userRequestNewFruit.name());
        fruitEntity.setWeightInKilos(userRequestNewFruit.weightInKilos());

        Fruit newFruit = repository.save(fruitEntity);

        return new FruitResponseDTO(
                newFruit.getId(),
                newFruit.getName(),
                newFruit.getWeightInKilos()
        );
    }

    @Override
    public FruitResponseDTO getById(Long fruitId) {
        return repository.findById(fruitId)
                .map(fruit -> new FruitResponseDTO(
                        fruit.getId(),
                        fruit.getName(),
                        fruit.getWeightInKilos()
                ))
                .orElseThrow(()->new RuntimeException("Fruit not found with id: "+fruitId));
    }
}
