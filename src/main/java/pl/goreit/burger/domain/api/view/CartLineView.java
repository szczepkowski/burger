package pl.goreit.burger.domain.api.view;

public class CartLineView {

    private Integer No;
    private BurgerView burgerView;
    private Integer amount;

    public CartLineView() {
    }

    public CartLineView(Integer no, BurgerView burgerView, Integer amount) {
        this.No = no;
        this.burgerView = burgerView;
        this.amount = amount;
    }

    public Integer getNo() {
        return No;
    }

    public void setNo(Integer no) {
        No = no;
    }

    public BurgerView getBurgerView() {
        return burgerView;
    }

    public void setBurgerView(BurgerView burgerView) {
        this.burgerView = burgerView;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
