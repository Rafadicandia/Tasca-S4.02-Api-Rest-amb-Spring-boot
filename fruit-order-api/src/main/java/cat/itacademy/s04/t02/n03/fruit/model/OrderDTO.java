package cat.itacademy.s04.t02.n03.fruit.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record OrderDTO(
        @NotBlank(message = "Name can't be blank")

        String clientName,
        @Future(message = "Delivery date must be issued for tomorrow or later")
        LocalDate deliveryDate,
        @NotEmpty(message = "The order must have items in it")
        @Valid
        List<OrderItem> items
) {
}
