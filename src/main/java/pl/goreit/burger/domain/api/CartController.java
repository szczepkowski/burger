package pl.goreit.burger.domain.api;

import org.springframework.web.bind.annotation.*;
import pl.goreit.burger.domain.api.view.CartLineView;
import pl.goreit.burger.domain.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping()
    public boolean addCartLine(@RequestBody CartLineView cartLineView) {
        cartService.addCartLine("admin", cartLineView.getBurgerView(), cartLineView.getAmount());
        return true;
    }

    @DeleteMapping()
    public boolean deleteCartLine(Integer no) {
        cartService.deleteCartLine("admin", no);
        return true;
    }
}
