package pl.goreit.burger.domain.api.view;

public class ProfileView {

    private String login;
    private CartView cartView;

    public ProfileView() {
    }

    public ProfileView(String login, CartView cartView) {
        this.login = login;
        this.cartView = cartView;
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
}
