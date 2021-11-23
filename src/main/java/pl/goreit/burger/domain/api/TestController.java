package pl.goreit.burger.domain.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.goreit.burger.domain.model.Burger;
import pl.goreit.burger.infrastructure.BurgerRepo;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

@RestController
public class TestController {

    private final BurgerRepo burgerRepo;

    public TestController(BurgerRepo burgerRepo) {
        this.burgerRepo = burgerRepo;
    }

    @PostMapping
    public boolean generateBurgers() {
        Random random = new Random(100);
        Burger burger = new Burger(UUID.randomUUID().toString(), "XXX" + random, BigDecimal.valueOf(random.nextDouble()));
        this.burgerRepo.insert(burger);

        return true;
    }
}
