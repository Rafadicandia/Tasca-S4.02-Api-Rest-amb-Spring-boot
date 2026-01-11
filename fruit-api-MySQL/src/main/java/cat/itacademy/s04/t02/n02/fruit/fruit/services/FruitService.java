package cat.itacademy.s04.t02.n02.fruit.fruit.services;
import java.util.List;
import cat.itacademy.s04.t02.n02.fruit.fruit.model.FruitDTO;
import cat.itacademy.s04.t02.n02.fruit.fruit.model.FruitResponseDTO;

public interface FruitService {
    public FruitResponseDTO save(FruitDTO userRequestNewFruit);

    public FruitResponseDTO getById(Long fruitId);

    public List<FruitResponseDTO> getAll();

    public FruitResponseDTO update(Long id, FruitDTO userRequestUpdateFruit);

    void delete(Long id);

}
