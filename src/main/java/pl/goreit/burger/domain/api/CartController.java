package pl.goreit.burger.domain.api;

import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import pl.goreit.burger.domain.api.exceptions.EntityNotFoundException;
import pl.goreit.burger.domain.api.exceptions.WrongValueException;
import pl.goreit.burger.domain.api.view.BurgerView;
import pl.goreit.burger.domain.api.view.CartView;
import pl.goreit.burger.domain.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping()
    public CartView get() {
        return cartService.get("admin");
    }

    @PostMapping()
    public boolean addCartLine(@RequestParam String name, @RequestParam Integer amount) throws WrongValueException, EntityNotFoundException {
        cartService.addCartLine("admin", name, amount);
        return true;
    }

    @PutMapping()
    public boolean editCartLine(Integer no, Integer amount) throws EntityNotFoundException, WrongValueException {
        return cartService.editCartLine("admin", no, amount);
    }

    @DeleteMapping()
    public boolean deleteCartLine(Integer no) throws EntityNotFoundException {
        cartService.deleteCartLine("admin", no);
        return true;
    }

    @DeleteMapping("/reset")
    public boolean resetCart() {
        cartService.resetCart("admin");
        return true;
    }
}
