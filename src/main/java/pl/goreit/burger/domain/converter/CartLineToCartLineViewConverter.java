package pl.goreit.burger.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.goreit.burger.domain.api.view.CartLineView;
import pl.goreit.burger.domain.model.CartLine;

@Component
public class CartLineToCartLineViewConverter implements Converter<CartLine, CartLineView> {

    @Override
    public CartLineView convert(CartLine cartLine) {
        return new CartLineView(cartLine.getNo(), cartLine.getBurgerView(), cartLine.getAmount());
    }
}
