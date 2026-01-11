package cat.itacademy.s04.t02.n02.fruit.provider.controller;

import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/providers")
public class ProviderController {

    private final ProviderService service;

    @PostMapping
    public ResponseEntity<ProviderResponseDTO> create(@Valid @RequestBody ProviderDTO providerDTO) {
        ProviderResponseDTO response = service.create(providerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
