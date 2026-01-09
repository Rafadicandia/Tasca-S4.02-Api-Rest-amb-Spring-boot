package cat.itacademy.s04.s02.n01.fruit.services;

import cat.itacademy.s04.s02.n01.fruit.model.Fruit;
import cat.itacademy.s04.s02.n01.fruit.model.FruitDTO;
import cat.itacademy.s04.s02.n01.fruit.model.FruitResponseDTO;
import cat.itacademy.s04.s02.n01.fruit.repository.FruitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

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
                .orElseThrow(() -> new RuntimeException("Fruit not found with id: " + fruitId));
    }

    @Override
    public List<FruitResponseDTO> getAll() {
        List<FruitResponseDTO> allFruits = repository.findAll()
                .stream().map(fruit -> new FruitResponseDTO(
                        fruit.getId(),
                        fruit.getName(),
                        fruit.getWeightInKilos()
                ))
                .toList();
        return allFruits;
    }

    @Override
    public FruitResponseDTO update(Long id, FruitDTO userRequestUpdateFruit) {
        Fruit fruitToUpdate = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fruit not found with id: " + id));
        fruitToUpdate.setWeightInKilos(userRequestUpdateFruit.weightInKilos());
        fruitToUpdate.setName(userRequestUpdateFruit.name());

        Fruit updatedFruit = repository.save(fruitToUpdate);
        return new FruitResponseDTO(
                updatedFruit.getId(),
                updatedFruit.getName(),
                updatedFruit.getWeightInKilos()
        );
    }
}
