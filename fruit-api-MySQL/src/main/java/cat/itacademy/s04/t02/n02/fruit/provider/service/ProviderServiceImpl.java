package cat.itacademy.s04.t02.n02.fruit.provider.service;

import cat.itacademy.s04.t02.n02.fruit.fruit.model.FruitResponseDTO;
import cat.itacademy.s04.t02.n02.fruit.fruit.repository.FruitRepository;
import cat.itacademy.s04.t02.n02.fruit.provider.exception.*;
import cat.itacademy.s04.t02.n02.fruit.provider.model.Provider;
import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderResponseDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.repository.ProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository repository;
    private final FruitRepository fruitRepository;

    @Override
    public ProviderResponseDTO create(ProviderDTO providerDTO) {
        if (repository.existsByName(providerDTO.name())) {
            throw new ProviderNameDuplicatedException("Provider name already exist");
        }

        Provider provider = new Provider();
        provider.setName(providerDTO.name());
        provider.setCountry(providerDTO.country());

        Provider saved = repository.save(provider);

        return new ProviderResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getCountry(),
                List.of());
    }

    @Override
    public List<FruitResponseDTO> getFruitsByProvider(Long providerId) {

        if (!repository.existsById(providerId)) {
            throw new ProviderNotFoundException("Provider not found");
        }

        return fruitRepository.findByProviderId(providerId).stream()
                .map(fruit -> new FruitResponseDTO(
                        fruit.getId(),
                        fruit.getName(),
                        fruit.getWeightInKilos(),
                        fruit.getProvider().getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProviderResponseDTO> getAll() {
        List<ProviderResponseDTO> allProviders = repository.findAll()
                .stream().map(provider -> new ProviderResponseDTO(
                        provider.getId(),
                        provider.getName(),
                        provider.getCountry(),
                        List.of()
                ))
                .toList();
        return allProviders;
    }

    @Override
    public ProviderResponseDTO update(Long id, ProviderDTO dto) {

        Provider provider = repository.findById(id)
                .orElseThrow(() -> new ProviderNotFoundException("Provider not found with id: " + id));

        if (dto.name() == null || dto.name().isBlank()) {
            throw new ProviderNameCantBeEmpty("Name cannot be empty");
        }

        if (repository.existsByNameAndIdNot(dto.name(), id)) {
            throw new ProviderAlreadyExistsException("Another provider already has the name: " + dto.name());
        }

        provider.setName(dto.name());
        provider.setCountry(dto.country());

        return new ProviderResponseDTO(
                provider.getId(),
                provider.getName(),
                provider.getCountry(),
                List.of());
    }

    @Override
    public void delete(Long id) {
        Provider provider = repository.findById(id)
                .orElseThrow(() -> new ProviderNotFoundException("Provider not found with id: " + id));

        if (!provider.getFruits().isEmpty()) {
            throw new ProviderHasFruitsException("Cannot delete: Provider has associated fruits. Delete fruits first.");
        }

        repository.delete(provider);
    }

}
