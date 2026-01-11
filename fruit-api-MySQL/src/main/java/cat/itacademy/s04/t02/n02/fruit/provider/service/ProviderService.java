package cat.itacademy.s04.t02.n02.fruit.provider.service;

import cat.itacademy.s04.t02.n02.fruit.fruit.model.FruitResponseDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderResponseDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ProviderService {
    public ProviderResponseDTO create(ProviderDTO providerDTO);
    public List<FruitResponseDTO> getFruitsByProvider(Long providerId);
    public List<ProviderResponseDTO> getAll();
    public ProviderResponseDTO update(Long id, ProviderDTO dto);
    public void delete(Long id);
}
