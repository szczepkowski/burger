package pl.goreit.burger.domain.model;

import java.math.BigDecimal;

public class OrderLine {

    private String productName;
    private BigDecimal price;
    private Integer amount;

    public OrderLine(String productName, BigDecimal price, Integer amount) {
        this.productName = productName;
        this.price = price;
        this.amount = amount;
    }

    public OrderLine() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
