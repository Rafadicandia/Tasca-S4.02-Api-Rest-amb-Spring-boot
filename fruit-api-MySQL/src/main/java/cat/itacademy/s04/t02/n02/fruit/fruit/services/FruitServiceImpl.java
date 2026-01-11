package cat.itacademy.s04.t02.n02.fruit.fruit.services;

import cat.itacademy.s04.t02.n02.fruit.fruit.exception.FruitNotFoundException;


import cat.itacademy.s04.t02.n02.fruit.fruit.model.Fruit;
import cat.itacademy.s04.t02.n02.fruit.fruit.model.FruitDTO;
import cat.itacademy.s04.t02.n02.fruit.fruit.model.FruitResponseDTO;
import cat.itacademy.s04.t02.n02.fruit.fruit.repository.FruitRepository;
import cat.itacademy.s04.t02.n02.fruit.provider.model.Provider;
import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderResponseDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.repository.ProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@AllArgsConstructor
@Service
public class FruitServiceImpl implements FruitService {

    private final FruitRepository repository;
    private final ProviderRepository providerRepository;

    @Override
    public FruitResponseDTO save(FruitDTO userRequestNewFruit) {
        Provider provider = providerRepository.findById(userRequestNewFruit.providerId())
                .orElseThrow(()-> new ProviderNotFoundException("Provider not found with id: "+userRequestNewFruit.providerId()));

        Fruit fruitEntity = new Fruit();
        fruitEntity.setName(userRequestNewFruit.name());
        fruitEntity.setWeightInKilos(userRequestNewFruit.weightInKilos());
        fruitEntity.setProvider(provider);

        Fruit newFruit = repository.save(fruitEntity);

        return new FruitResponseDTO(
                newFruit.getId(),
                newFruit.getName(),
                newFruit.getWeightInKilos(),
                newFruit.getProvider().getName()
        );
    }

    @Override
    public FruitResponseDTO getById(Long fruitId) {
        return repository.findById(fruitId)
                .map(fruit -> new FruitResponseDTO(
                        fruit.getId(),
                        fruit.getName(),
                        fruit.getWeightInKilos(),
                        fruit.getProvider().getName()
                ))
                .orElseThrow(() -> new FruitNotFoundException(fruitId));
    }

    @Override
    public List<FruitResponseDTO> getAll() {
        List<FruitResponseDTO> allFruits = repository.findAll()
                .stream().map(fruit -> new FruitResponseDTO(
                        fruit.getId(),
                        fruit.getName(),
                        fruit.getWeightInKilos(),
                        fruit.getProvider().getName()
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
                updatedFruit.getWeightInKilos(),
                updatedFruit.getProvider().getName()
        );
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new FruitNotFoundException(id);
        }
        repository.deleteById(id);

    }



}
