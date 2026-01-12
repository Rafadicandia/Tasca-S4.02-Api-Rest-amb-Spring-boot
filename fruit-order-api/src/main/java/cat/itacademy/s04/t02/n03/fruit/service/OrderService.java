package cat.itacademy.s04.t02.n03.fruit.service;

import cat.itacademy.s04.t02.n03.fruit.model.Order;


public interface OrderService {
    public OrderResponseDTO createOrder(OrderDTO order);

    public List<OrderResponseDTO> getAllOrders();

    public OrderResponseDTO getOrderById(String id);

    public OrderResponseDTO updateOrder(String id, OrderDTO order);

    public void deleteOrder(String id);
}
