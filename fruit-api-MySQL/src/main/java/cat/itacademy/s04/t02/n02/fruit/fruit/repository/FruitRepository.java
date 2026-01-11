package cat.itacademy.s04.t02.n02.fruit.fruit.repository;


import cat.itacademy.s04.t02.n02.fruit.fruit.model.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
}
