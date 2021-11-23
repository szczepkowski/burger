package pl.goreit.burger.domain.api.view;

import pl.goreit.burger.domain.model.CartLine;

import java.util.List;

public class CartView {
    private List<CartLineView> cartLines;

    public CartView(List<CartLineView> cartLines) {
        this.cartLines = cartLines;
    }

    public List<CartLineView> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLineView> cartLines) {
        this.cartLines = cartLines;
    }
}
