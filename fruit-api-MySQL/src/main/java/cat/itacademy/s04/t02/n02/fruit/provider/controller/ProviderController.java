package cat.itacademy.s04.t02.n02.fruit.provider.controller;

import cat.itacademy.s04.t02.n02.fruit.fruit.model.FruitResponseDTO;
import cat.itacademy.s04.t02.n02.fruit.fruit.services.FruitService;
import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderResponseDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.service.ProviderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/providers")
public class ProviderController {

    private final ProviderService service;
    private final FruitService fruitService;

    @PostMapping
    public ResponseEntity<ProviderResponseDTO> create(@Valid @RequestBody ProviderDTO providerDTO) {
        ProviderResponseDTO response = service.create(providerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ProviderResponseDTO>> getAllProviders() {
        List<ProviderResponseDTO> providers = service.getAll();
        return ResponseEntity.ok(providers);
    }
    @GetMapping("/{id}/fruits")
    public ResponseEntity<List<FruitResponseDTO>> getFruitsByProvider(@PathVariable Long id) {
        List<FruitResponseDTO> fruits = service.getFruitsByProvider(id);
        return ResponseEntity.ok(fruits);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProviderResponseDTO> update(@PathVariable Long id, @RequestBody ProviderDTO dto){

        return ResponseEntity.ok(service.update(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
