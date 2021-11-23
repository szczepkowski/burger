package pl.goreit.burger.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Document
public class Burger {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private BigDecimal price;

    private Status status;
    private LocalDateTime createdAt;

    public Burger(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = LocalDateTime.now();
        this.status = Status.ACTIVE;
    }

    public boolean markAsDeleted() {
        this.status = Status.DELETED;
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        ACTIVE, ON_HOLD, DELETED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Burger)) return false;
        Burger burger = (Burger) o;
        return Objects.equals(id, burger.id) && Objects.equals(name, burger.name) && Objects.equals(price, burger.price) && status == burger.status && Objects.equals(createdAt, burger.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, status, createdAt);
    }
}
