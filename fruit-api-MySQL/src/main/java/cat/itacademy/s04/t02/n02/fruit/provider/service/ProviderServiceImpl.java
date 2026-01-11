package cat.itacademy.s04.t02.n02.fruit.provider.service;

import cat.itacademy.s04.t02.n02.fruit.fruit.model.FruitResponseDTO;
import cat.itacademy.s04.t02.n02.fruit.fruit.repository.FruitRepository;
import cat.itacademy.s04.t02.n02.fruit.provider.exception.ProviderNameDuplicatedException;
import cat.itacademy.s04.t02.n02.fruit.provider.model.Provider;
import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderResponseDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.repository.ProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository repository;
    private final FruitRepository fruitRepository;

    @Override
    public ProviderResponseDTO create(ProviderDTO providerDTO) {
        if (repository.existsByName(providerDTO.name())){
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

    public ProviderResponseDTO getProviderWithFruits(Long providerId) {
        Provider provider = repository.findById(providerId)
                .orElseThrow(() -> new ProviderNotFoundException("Provider not found"));

        return mapToResponseDTO(provider); // Aquí sí funciona porque le pasas un Provider
    }

    public ProviderResponseDTO mapToResponseDTO(Provider provider) {
        List<FruitResponseDTO> fruitDTOs = provider.getFruits().stream()
                .map(fruit -> new FruitResponseDTO(fruit.getId(), fruit.getName(), fruit.getWeightInKilos(), fruit.getProvider()))
                .toList();

        return new ProviderResponseDTO(
                provider.getId(),
                provider.getName(),
                provider.getCountry(),
                fruitDTOs
        );
    }
}
