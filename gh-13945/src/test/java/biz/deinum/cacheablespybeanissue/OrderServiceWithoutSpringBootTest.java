package biz.deinum.cacheablespybeanissue;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Test case to show the issue with the ordering of the
 * creation of the Spy on a Spring Managed bean.
 *
 * @author Marten Deinum.
 */
public class OrderServiceWithoutSpringBootTest {

    private AnnotationConfigApplicationContext ctx;

    @Before
    public void setup() {
        ctx = new AnnotationConfigApplicationContext(TestConfiguration.class);
    }

    @After
    public void after() {
        ctx.close();
    }

    /**
     * First let Spring do its work before wrapping the object in a Mockito
     * proxy to add the spy behavior. This will succeed.
     */
    @Test
    public void manualSpyTestCreateSpringProxyFirst() {
        OrderService service = new OrderService();
        ctx.getAutowireCapableBeanFactory().autowireBean(service);
        service = (OrderService) ctx.getAutowireCapableBeanFactory().initializeBean(service, "orderService");
        OrderService spyService = Mockito.spy(service);

        PartialKey key = new PartialKey("21");

        System.out.println(spyService.find("123", key));
        System.out.println(spyService.find("123", key));
        System.out.println(spyService.find("123", key));
        verify(spyService, times(1)).find("123", key);
    }

    /**
     * This test mimics the behavior of the Spring Boot SpyPostProcessor by
     * first creating a spy and then let Spring do its work. This will fail.
     *
     * Apparently the debug information gets lost while proxying the already
     * proxied instance.
     */
    @Test
    public void manualSpyTestCreateMockitoProxyFirst() {
        OrderService service = Mockito.spy(new OrderService());
        ctx.getAutowireCapableBeanFactory().autowireBean(service);
        service = (OrderService) ctx.getAutowireCapableBeanFactory().initializeBean(service, "orderService");

        PartialKey key = new PartialKey("21");

        System.out.println(service.find("123", key));
        System.out.println(service.find("123", key));
        System.out.println(service.find("123", key));

        verify(service, times(1)).find("123", key);
    }

    @Configuration
    @EnableCaching
    public static class TestConfiguration {

        @Bean
        public CacheManager cacheManager() {
            SimpleCacheManager cacheManager = new SimpleCacheManager();
            cacheManager.setCaches(Collections.singletonList(new ConcurrentMapCache("orders")));
            return cacheManager;
        }
    }

}