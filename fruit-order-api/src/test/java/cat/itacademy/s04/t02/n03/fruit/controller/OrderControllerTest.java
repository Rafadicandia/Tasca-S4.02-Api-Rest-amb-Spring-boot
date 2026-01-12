package cat.itacademy.s04.t02.n03.fruit.controller;

import cat.itacademy.s04.t02.n03.fruit.model.Order;
import cat.itacademy.s04.t02.n03.fruit.model.OrderDTO;
import cat.itacademy.s04.t02.n03.fruit.model.OrderItem;
import cat.itacademy.s04.t02.n03.fruit.model.OrderResponseDTO;
import cat.itacademy.s04.t02.n03.fruit.repository.OrderRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class OrderControllerTest {

    @Autowired
    private OrderRepository orderRepository;

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    List<OrderItem> items = List.of(new OrderItem("Apple", 5));

    @Test
    void shouldCreateOrderSuccessfully() {

        OrderItem item = new OrderItem("Manzana", 10);
        OrderDTO request = new OrderDTO("Cliente Test", LocalDate.now().plusDays(2), List.of(item));

        ResponseEntity<OrderResponseDTO> response = restTemplate.postForEntity("/orders", request, OrderResponseDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().id());
        assertEquals("Cliente Test", response.getBody().clientName());
    }

    @Test
    void shouldReturn404WhenOrderNotFound() {

        ResponseEntity<String> response = restTemplate.getForEntity("/orders/987", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldGetAllOrders() {
        orderRepository.save(new Order(null, "Test User", LocalDate.now().plusDays(1), List.of()));

        ResponseEntity<List> response = restTemplate.getForEntity("/orders", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void shouldUpdateOrderSuccessfully() {

        Order saved = orderRepository.save(new Order(null, "Original Name", LocalDate.now(), items));
        String id = saved.getId();

        OrderDTO updateRequest = new OrderDTO("Updated Name", LocalDate.now().plusDays(2), items);

        ResponseEntity<OrderResponseDTO> response = restTemplate.exchange(
                "/orders/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(updateRequest),
                OrderResponseDTO.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Name", response.getBody().clientName());
    }

    @Test
    void shouldDeleteOrderSuccessfully() {

        Order saved = orderRepository.save(new Order(null, "To Delete", LocalDate.now(), List.of()));

        restTemplate.delete("/orders/" + saved.getId());

        assertFalse(orderRepository.existsById(saved.getId()));
    }

    @Test
    void shouldReturn404WhenUpdatingNonExistentOrder() {
        String nonExistentId = "60f71820f672322634327999";

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        List<OrderItem> items = List.of(new OrderItem("Apple", 5));
        OrderDTO updateRequest = new OrderDTO("Valid Name", tomorrow, items);

        ResponseEntity<String> response = restTemplate.exchange(
                "/orders/" + nonExistentId,
                HttpMethod.PUT,
                new HttpEntity<>(updateRequest),
                String.class
        );
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}