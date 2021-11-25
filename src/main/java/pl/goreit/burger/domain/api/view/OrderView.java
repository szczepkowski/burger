package pl.goreit.burger.domain.api.view;

import org.springframework.stereotype.Component;
import pl.goreit.burger.domain.model.OrderLine;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public class OrderView {
    private String orderId;

    private List<OrderLineView> orderLineViews;
    private BigDecimal orderCost;

    private boolean atLocation;

    private LocalDateTime createdAt;

    public OrderView(String orderId, List<OrderLineView> orderLineViews, BigDecimal orderCost, boolean atLocation, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.orderLineViews = orderLineViews;
        this.orderCost = orderCost;
        this.atLocation = atLocation;
        this.createdAt = createdAt;
    }

    public OrderView() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderLineView> getOrderLineViews() {
        return orderLineViews;
    }

    public void setOrderLineViews(List<OrderLineView> orderLineViews) {
        this.orderLineViews = orderLineViews;
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
