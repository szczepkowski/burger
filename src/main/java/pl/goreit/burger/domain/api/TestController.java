package pl.goreit.burger.domain.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.goreit.burger.domain.model.Burger;
import pl.goreit.burger.domain.model.Cart;
import pl.goreit.burger.domain.model.Profile;
import pl.goreit.burger.infrastructure.BurgerRepo;
import pl.goreit.burger.infrastructure.ProfileRepo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

@RestController("/tests")
public class TestController {

    private final BurgerRepo burgerRepo;
    private final ProfileRepo profileRepo;

    public TestController(BurgerRepo burgerRepo, ProfileRepo profileRepo) {
        this.burgerRepo = burgerRepo;
        this.profileRepo = profileRepo;
    }

    @PostMapping("/burger")
    public boolean generateBurgers() {
        Random random = new Random(100);
        Burger burger = new Burger(UUID.randomUUID().toString(), "XXX" + random, BigDecimal.valueOf(random.nextDouble()));
        this.burgerRepo.insert(burger);

        return true;
    }

    @PostMapping("/cart1")
    public void initCart(String login) {
        this.profileRepo.save(new Profile(UUID.randomUUID().toString(), login, new Cart( Arrays.asList())));
    }
}
