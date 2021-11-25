package pl.goreit.burger.domain.converter;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.goreit.burger.domain.api.view.ProfileView;
import pl.goreit.burger.domain.model.Profile;

@Component
public class ProfileFromModelToViewConverter implements Converter<Profile, ProfileView> {

    private final CartFromModelToViewConverter cart;

    @Lazy
    public ProfileFromModelToViewConverter(CartFromModelToViewConverter cart) {
        this.cart = cart;
    }

    @Override
    public ProfileView convert(Profile profile) {
        return new ProfileView(profile.getLogin(), cart.convert(profile.getCart()));
    }
}
