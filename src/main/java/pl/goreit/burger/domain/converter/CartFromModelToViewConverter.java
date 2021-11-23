package pl.goreit.burger.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.goreit.burger.domain.api.view.CartView;
import pl.goreit.burger.domain.model.Cart;

import java.util.stream.Collectors;

@Component
public class CartFromModelToViewConverter implements Converter<Cart, CartView> {

    private final CartLineToCartLineViewConverter cartLineToCartLineViewConverter;

    public CartFromModelToViewConverter(CartLineToCartLineViewConverter cartLineToCartLineViewConverter) {
        this.cartLineToCartLineViewConverter = cartLineToCartLineViewConverter;
    }

    @Override
    public CartView convert(Cart cart) {
        return new CartView(cart.getCartLines().stream()
                .map(cartLineToCartLineViewConverter::convert)
                .collect(Collectors.toList())
        );
    }
}
