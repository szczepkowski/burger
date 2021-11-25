package pl.goreit.burger.domain.model;

import pl.goreit.burger.domain.api.exceptions.EntityNotFoundException;
import pl.goreit.burger.domain.api.exceptions.WrongValueException;
import pl.goreit.burger.domain.api.view.BurgerView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cart {

    private List<CartLine> cartLines;


    public Cart(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    public boolean addCartLine(BurgerView burgerView, Integer amount) {
        int size = this.cartLines.size() + 1;
        return this.cartLines.add(new CartLine(size - 1, burgerView, amount));
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

        return true;
    }

    public boolean reset() {
         this.cartLines.clear();
         return true;
    }


    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }


}
