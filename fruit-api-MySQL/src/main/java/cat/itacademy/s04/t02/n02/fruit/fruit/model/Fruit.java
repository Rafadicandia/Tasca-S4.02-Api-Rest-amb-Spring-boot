package cat.itacademy.s04.t02.n02.fruit.fruit.model;

import cat.itacademy.s04.t02.n02.fruit.provider.model.Provider;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="fruits")
public class Fruit {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int weightInKilos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="provider_id", nullable = false)
    private Provider provider;


}
