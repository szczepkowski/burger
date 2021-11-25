package pl.goreit.burger.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.goreit.burger.domain.api.exceptions.EntityNotFoundException;
import pl.goreit.burger.domain.api.exceptions.WrongValueException;
import pl.goreit.burger.domain.api.view.BurgerView;
import pl.goreit.burger.domain.api.view.CartView;
import pl.goreit.burger.domain.converter.CartFromModelToViewConverter;
import pl.goreit.burger.domain.model.Cart;
import pl.goreit.burger.domain.model.Profile;
import pl.goreit.burger.infrastructure.ProfileRepo;

@Service
public class CartService {

    private final BurgerService burgerService;

    private final CartFromModelToViewConverter cartFromModelToViewConverter;

    private final ProfileRepo profileRepo;

    public CartService(ProfileRepo profileRepo, CartFromModelToViewConverter cartFromModelToViewConverter, BurgerService burgerService) {
        this.profileRepo = profileRepo;

        this.cartFromModelToViewConverter = cartFromModelToViewConverter;
        this.burgerService = burgerService;
    }

    public CartView get(String login) {
        Profile profile = this.profileRepo.findByLogin(login);
        Cart cart = profile.getCart();
        return cartFromModelToViewConverter.convert(cart);
    }

    public boolean editCartLine(String login, Integer no, Integer amount) throws EntityNotFoundException, WrongValueException {
        Profile profile = profileRepo.findByLogin(login);
        Cart cart = profile.getCart();
        cart.editCartLine(no, amount);
        profileRepo.save(profile);
        return true;
    }

    public boolean addCartLine(String login, String name, Integer amount) throws WrongValueException, EntityNotFoundException {

        if (amount < 0) {
            throw new WrongValueException("Amount has to be positive number instead of :" + amount);
        }

        BurgerView burgerView = burgerService.get(name);
        if (burgerView == null) {
            throw new EntityNotFoundException("Burger with name not exist " + name);
        }

        Profile byLogin = profileRepo.findByLogin(login);
        Cart cart = byLogin.getCart();
        cart.addCartLine(burgerView, amount);
        Profile saved = profileRepo.save(byLogin);
        return true;
    }

    public boolean deleteCartLine(String login, Integer No) throws EntityNotFoundException {
        Profile byLogin = profileRepo.findByLogin(login);
        Cart cart = byLogin.getCart();
        cart.deleteCartLine(No);
        Profile saved = profileRepo.save(byLogin);
        return true;
    }

    public  boolean resetCart(String login){
        Profile profile = profileRepo.findByLogin(login);
        Cart cart = profile.getCart();
        cart.reset();
        profileRepo.save(profile);
        return true;
    }
}
