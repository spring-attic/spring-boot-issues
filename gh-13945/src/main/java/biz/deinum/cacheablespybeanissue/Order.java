package biz.deinum.cacheablespybeanissue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Order implements Serializable {

    private String id;
    private BigDecimal amount;

    public Order(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(amount, order.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @Override
    public String toString() {
        return String.format("Order (id=%s, amount=%s)", this.id, this.amount);
    }
}
