package pl.goreit.burger.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.goreit.burger.domain.api.view.OrderLineView;
import pl.goreit.burger.domain.api.view.OrderView;
import pl.goreit.burger.domain.api.view.ProfileView;
import pl.goreit.burger.domain.model.OrderLine;
import pl.goreit.burger.domain.model.Profile;

import java.util.stream.Collectors;

@Component
public class ProfileFromModelToViewConverter implements Converter<Profile, ProfileView> {

    private final CartFromModelToViewConverter cart;

    private final OrderFromModelToViewConverter orderFromModelToViewConverter;

    @Lazy
    public ProfileFromModelToViewConverter(CartFromModelToViewConverter cart, OrderFromModelToViewConverter orderFromModelToViewConverter) {
        this.cart = cart;
        this.orderFromModelToViewConverter = orderFromModelToViewConverter;
    }

    @Override
    public ProfileView convert(Profile profile) {
        return new ProfileView(profile.getLogin(), cart.convert(profile.getCart()), profile.getOrders().stream()
                .map(order -> {
                    return new OrderView(order.getOrderId(), order.getOrderLines().stream()
                            .map(orderLine -> {
                                return new OrderLineView(orderLine.getProductName(), orderLine.getPrice(), orderLine.getAmount());
                            }).collect(Collectors.toList()),
                            order.getOrderCost(),
                            order.isAtLocation(),
                            order.getCreatedAt());
                }).collect(Collectors.toList()));
    }
}
