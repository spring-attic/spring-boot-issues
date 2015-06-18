package demo;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "withItems", types = Order.class)
interface OrderWithItems {

    String getName();

    List<Item> getItems();
}
