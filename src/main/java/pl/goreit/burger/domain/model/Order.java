package pl.goreit.burger.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Order {

    private String orderId;

    private List<OrderLine> orderLines;
    private BigDecimal orderCost;

    private boolean atLocation;

    private LocalDateTime createdAt;

    public Order(Cart cart, boolean atLocation) {
        this.orderId = UUID.randomUUID().toString();
        this.orderCost = cart.getCartCost();

        this.orderLines = cart.getCartLines().stream()
                .map(cartLine -> new OrderLine(cartLine.getBurgerView().getName(), cartLine.getBurgerView().getPrice(), cartLine.getAmount()))
                .collect(Collectors.toList());

        this.atLocation = atLocation;
        this.createdAt = LocalDateTime.now();
    }

    public Order(String orderId, List<OrderLine> orderLines) {
        this.orderId = orderId;
        this.orderLines = orderLines;
        this.atLocation = true;

        this.createdAt = LocalDateTime.now();
    }

    public Order() {
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    public boolean isAtLocation() {
        return atLocation;
    }

    public void setAtLocation(boolean atLocation) {
        this.atLocation = atLocation;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
