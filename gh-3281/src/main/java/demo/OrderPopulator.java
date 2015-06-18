package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class OrderPopulator {

    @Autowired
    public OrderPopulator(OrderRepository orders) {

        Order order = new Order("o1");
        order.addItem(new Item("i1"));
        order.addItem(new Item("i2"));
        orders.save(order);
    }
}
