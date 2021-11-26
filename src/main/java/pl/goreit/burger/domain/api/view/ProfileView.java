package pl.goreit.burger.domain.api.view;

import pl.goreit.burger.domain.model.Order;

import java.util.ArrayList;
import java.util.List;

public class ProfileView {

    private String login;
    private CartView cartView;
    private List<OrderView> orderViews;

    public ProfileView() {
    }

    public ProfileView(String login, CartView cartView, List<OrderView> orders) {
        this.login = login;
        this.cartView = cartView;
        this.orderViews = orders;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public CartView getCartView() {
        return cartView;
    }

    public void setCartView(CartView cartView) {
        this.cartView = cartView;
    }

    public List<OrderView> getOrderViews() {
        return orderViews;
    }

    public void setOrderViews(List<OrderView> orderViews) {
        this.orderViews = orderViews;
    }
}
