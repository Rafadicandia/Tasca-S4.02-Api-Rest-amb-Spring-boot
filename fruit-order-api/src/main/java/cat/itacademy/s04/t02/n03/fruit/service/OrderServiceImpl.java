package cat.itacademy.s04.t02.n03.fruit.service;

import cat.itacademy.s04.t02.n03.fruit.exception.OrderNotFoundException;
import cat.itacademy.s04.t02.n03.fruit.model.Order;
import cat.itacademy.s04.t02.n03.fruit.model.OrderDTO;
import cat.itacademy.s04.t02.n03.fruit.model.OrderResponseDTO;
import cat.itacademy.s04.t02.n03.fruit.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderResponseDTO createOrder(OrderDTO orderDTO) {

        Order order = mapToEntity(orderDTO);

        Order savedOrder = orderRepository.save(order);

        if (savedOrder == null){
            throw new RuntimeException("Failed to save order in Mongodb");
        }

            return mapToResponseDTO(savedOrder);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO getOrderById(String id) {
        return orderRepository.findById(id)
                .map(this::mapToResponseDTO)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }

    @Override
    public OrderResponseDTO updateOrder(String id, OrderDTO orderDTO) {
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    Order toUpdate = mapToEntity(orderDTO);
                    toUpdate.setId(id);
                    return mapToResponseDTO(orderRepository.save(toUpdate));
                })
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }

    @Override
    public void deleteOrder(String id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    private Order mapToEntity(OrderDTO dto) {
        return new Order(null, dto.clientName(), dto.deliveryDate(), dto.items());
    }

    private OrderResponseDTO mapToResponseDTO(Order entity) {
        return new OrderResponseDTO(
                entity.getId(),
                entity.getClientName(),
                entity.getDeliveryDate(),
                entity.getItems()
        );
    }
}
