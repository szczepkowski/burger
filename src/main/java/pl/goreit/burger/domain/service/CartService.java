package pl.goreit.burger.domain.service;

import org.springframework.stereotype.Service;
import pl.goreit.burger.domain.api.view.BurgerView;
import pl.goreit.burger.domain.model.Cart;
import pl.goreit.burger.domain.model.Profile;
import pl.goreit.burger.infrastructure.ProfileRepo;

@Service
public class CartService {

    private final ProfileRepo profileRepo;

    public CartService(ProfileRepo profileRepo ) {
        this.profileRepo = profileRepo;

    }

    public boolean addCartLine(String login, BurgerView burgerView, Integer amount) {
        Profile byLogin = profileRepo.findByLogin(login);
        Cart cart = byLogin.getCart();
        cart.addCartLine(burgerView, amount);
        Profile saved = profileRepo.save(byLogin);
        return true;
    }

    public boolean deleteCartLine(String login, Integer No) {
        Profile byLogin = profileRepo.findByLogin(login);
        Cart cart = byLogin.getCart();
        cart.deleteCartLine(No);
        Profile saved = profileRepo.save(byLogin);
        return true;
    }
}
