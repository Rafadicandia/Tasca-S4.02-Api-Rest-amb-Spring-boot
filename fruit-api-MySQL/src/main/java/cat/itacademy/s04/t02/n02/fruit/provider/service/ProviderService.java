package cat.itacademy.s04.t02.n02.fruit.provider.service;

import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderResponseDTO;

public interface ProviderService {
    public ProviderResponseDTO create(ProviderDTO providerDTO);
}
