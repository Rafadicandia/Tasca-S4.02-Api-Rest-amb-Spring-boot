package cat.itacademy.s04.t02.n03.fruit.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class OrderControllerTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateOrderSuccessfully() {
        // GIVEN: Un OrderDTO válido (recuerda usar una fecha futura)
        // OrderDTO request = new OrderDTO("Juan", LocalDate.now().plusDays(1), List.of(...));

        // WHEN: Hacemos el POST
        // ResponseEntity<OrderResponseDTO> response = restTemplate.postForEntity("/orders", request, OrderResponseDTO.class);

        // THEN: Verificamos el status 201
        // assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}