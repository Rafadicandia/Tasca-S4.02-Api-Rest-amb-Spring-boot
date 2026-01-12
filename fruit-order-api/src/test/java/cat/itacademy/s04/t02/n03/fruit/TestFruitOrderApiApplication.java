package cat.itacademy.s04.t02.n03.fruit;

import org.springframework.boot.SpringApplication;

public class TestFruitOrderApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(FruitOrderApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
