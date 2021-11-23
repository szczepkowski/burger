package pl.goreit.burger.domain.model;

import org.springframework.data.annotation.Id;
import pl.goreit.burger.domain.api.view.BurgerView;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cart {

    @Id
    private String id;
    private List<CartLine> cartLines;


    public Cart(String id, List<CartLine> cartLines) {
        this.id = id;
        this.cartLines = cartLines;
    }

    public boolean addCartLine(BurgerView burgerView, Integer amount) {
        int size = this.cartLines.size();
        return this.cartLines.add(new CartLine(size - 1, burgerView, amount));
    }

    public boolean deleteCartLine(Integer no) {
        this.cartLines = this.cartLines.stream().filter(cartLine -> !Objects.equals(cartLine.getNo(), no))
                .collect(Collectors.toList());
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }


}
