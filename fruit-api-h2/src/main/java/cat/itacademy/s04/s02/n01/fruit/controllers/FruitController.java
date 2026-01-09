package cat.itacademy.s04.s02.n01.fruit.controllers;

import cat.itacademy.s04.s02.n01.fruit.model.Fruit;
import cat.itacademy.s04.s02.n01.fruit.model.FruitDTO;
import cat.itacademy.s04.s02.n01.fruit.model.FruitResponseDTO;
import cat.itacademy.s04.s02.n01.fruit.services.FruitService;
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
@RequestMapping("/fruits")
public class FruitController {

    private final FruitService service;

    @PostMapping
    public ResponseEntity<FruitResponseDTO> createFruit(
            @Valid @RequestBody FruitDTO fruitRequest) {

        FruitResponseDTO fruitResponse = service.save(fruitRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(fruitResponse);
    }

}
