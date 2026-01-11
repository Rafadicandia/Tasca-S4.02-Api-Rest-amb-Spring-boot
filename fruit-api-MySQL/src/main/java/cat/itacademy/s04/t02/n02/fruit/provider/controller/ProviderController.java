package cat.itacademy.s04.t02.n02.fruit.provider.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/providers")
public class ProviderController {

    private final ProviderService service;
}
