package cat.itacademy.s04.t02.n03.fruit.service;

import cat.itacademy.s04.t02.n03.fruit.model.Order;
import cat.itacademy.s04.t02.n03.fruit.model.OrderDTO;
import cat.itacademy.s04.t02.n03.fruit.model.OrderResponseDTO;
import cat.itacademy.s04.t02.n03.fruit.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderResponseDTO createOrder(OrderDTO orderDTO) {

        Order order = mapToEntity(orderDTO);

        Order savedOrder = orderRepository.save(order);

        return mapToResponseDTO(savedOrder);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return null;
    }

    @Override
    public OrderResponseDTO getOrderById(String id) {
        return null;
    }

    @Override
    public OrderResponseDTO updateOrder(String id, OrderDTO order) {
        return null;
    }

    @Override
    public void deleteOrder(String id) {

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
