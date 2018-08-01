package biz.deinum.cacheablespybeanissue;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Cacheable(value="orders", key = "#id + #pk.field1")
    public Order find(String id, PartialKey pk) {

        double amount = ThreadLocalRandom.current().nextDouble(1000.00);
        Order order = new Order(id, BigDecimal.valueOf(amount));
        logger.info("Created: {}", order);
        return order;
    }
}
