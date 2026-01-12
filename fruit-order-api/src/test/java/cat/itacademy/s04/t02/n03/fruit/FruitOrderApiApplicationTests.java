package cat.itacademy.s04.t02.n03.fruit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class FruitOrderApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
