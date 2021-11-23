package pl.goreit.burger.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.goreit.burger.domain.api.view.BurgerView;
import pl.goreit.burger.domain.model.Burger;

@Component
public class BurgerFromModelToViewConverter  implements Converter<Burger, BurgerView> {

    @Override
    public BurgerView convert(Burger burger) {
        return new BurgerView(burger.getName(), burger.getPrice() );
    }
}
