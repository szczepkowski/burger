package pl.goreit.burger.domain.model;

import pl.goreit.burger.domain.api.exceptions.EntityNotFoundException;
import pl.goreit.burger.domain.api.exceptions.WrongValueException;
import pl.goreit.burger.domain.api.view.BurgerView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cart {

    private List<CartLine> cartLines;
    private BigDecimal cartCost = BigDecimal.ZERO;


    public Cart(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    public boolean addCartLine(BurgerView burgerView, Integer amount) {
        int size = this.cartLines.size() + 1;
        CartLine cartLine = new CartLine(size - 1, burgerView, amount);
        this.cartLines.add(cartLine);
        this.recalculatCosts();
        return true;

    }

    public boolean editCartLine(Integer no, Integer amount) throws EntityNotFoundException, WrongValueException {
        Optional<CartLine> cartLine1 = this.cartLines.stream()
                .filter(cartLine -> Objects.equals(cartLine.getNo(), no))
                .findAny();

        CartLine cartLine = cartLine1.orElseThrow(() -> new EntityNotFoundException("cartLine not found " + no));

        if (amount <= 0) {
            throw new WrongValueException("Amount has to be positive : " + amount);
        }

        cartLine.setAmount(amount);

        this.recalculatCosts();
        return true;
    }

    public boolean deleteCartLine(Integer no) throws EntityNotFoundException {

        boolean exist = cartLines.stream().
                anyMatch(cartLine -> Objects.equals(cartLine.getNo(), no));

        if (!exist) {
            throw new EntityNotFoundException("CartLine not exists " + no);
        }

        this.cartLines = this.cartLines.stream().filter(cartLine -> !Objects.equals(cartLine.getNo(), no))
                .collect(Collectors.toList());

        this.recalculatCosts();
        return true;
    }

    public boolean reset() {
        this.cartLines.clear();
        this.recalculatCosts();
        return true;
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    public void recalculatCosts() {
        this.cartCost = BigDecimal.ZERO;

        this.cartLines.forEach(cartLine -> {
            Integer amount = cartLine.getAmount();
            BigDecimal price = cartLine.getBurgerView().getPrice();
            BigDecimal cartLineCost = price.multiply(BigDecimal.valueOf(amount));
            this.cartCost = this.cartCost.add(cartLineCost);

        });
    }

    public BigDecimal getCartCost() {
        return cartCost;
    }

    public void setCartCost(BigDecimal cartCost) {
        this.cartCost = cartCost;
    }
}
