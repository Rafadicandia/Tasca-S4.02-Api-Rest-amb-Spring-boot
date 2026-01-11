package cat.itacademy.s04.t02.n02.fruit.provider.service;

import cat.itacademy.s04.t02.n02.fruit.provider.model.Provider;
import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderResponseDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.repository.ProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository repository;

    @Override
    public ProviderResponseDTO create(ProviderDTO providerDTO) {
        if (repository.existsByName(providerDTO.name())){
            throw new RuntimeException("Provider name already exist");
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
}
