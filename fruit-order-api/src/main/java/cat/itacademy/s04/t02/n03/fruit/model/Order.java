package cat.itacademy.s04.t02.n03.fruit.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    String id;

    String clientName;

    LocalDate deliveryDate;

    List<OrderItem> items;
}
