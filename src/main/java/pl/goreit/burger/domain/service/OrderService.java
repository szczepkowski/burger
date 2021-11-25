package pl.goreit.burger.domain.service;

import org.springframework.stereotype.Service;
import pl.goreit.burger.domain.api.exceptions.EntityNotFoundException;
import pl.goreit.burger.domain.api.view.OrderView;
import pl.goreit.burger.domain.api.view.ProfileView;
import pl.goreit.burger.domain.model.Cart;
import pl.goreit.burger.domain.model.Order;
import pl.goreit.burger.domain.model.Profile;
import pl.goreit.burger.infrastructure.ProfileRepo;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final ProfileRepo profileRepo;

    private final ProfileService profileService;

    public OrderService(ProfileRepo profileRepo, ProfileService profileService) {
        this.profileRepo = profileRepo;
        this.profileService = profileService;
    }

    public OrderView create(String login, boolean atLocation) throws EntityNotFoundException {
        Profile profile = this.profileRepo.findByLogin(login);
        Cart cart = profile.getCart();
        Order order = new Order(cart, atLocation);
        profile.addOrder(order);
        cart.reset();

        Profile savedProfile = profileRepo.save(profile);


        ProfileView profileView = profileService.get(login);
        Optional<OrderView> savedOrderView = profileView.getOrderViews().stream()
                .filter(orderView -> orderView.getOrderId().equals(order.getOrderId()))
                .findFirst();


        return savedOrderView.orElseThrow(() -> new EntityNotFoundException("Order id not found = " + order.getOrderId()));
    }

    public List<OrderView> getAll(String login) {
        ProfileView profileView = profileService.get(login);

        return profileView.getOrderViews();
    }
}
