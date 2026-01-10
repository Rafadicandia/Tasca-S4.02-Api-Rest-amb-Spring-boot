package cat.itacademy.s04.t02.n02.fruit.provider.model;


import cat.itacademy.s04.t02.n02.fruit.fruit.model.Fruit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "providers")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Name is required")
    private String name;

    @OneToMany(mappedBy = "provider")
    private List<Fruit> fruits;

}
