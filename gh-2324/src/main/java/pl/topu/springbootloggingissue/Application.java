package pl.topu.springbootloggingissue;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    // Comment out this bean to cause memory leak
    @Bean
    public ApplicationListener<ContextClosedEvent> uninstallSLF4JBridgeHandlerWorkaround() {
        return new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                try {
                    SLF4JBridgeHandler.removeHandlersForRootLogger();
                } catch (NoSuchMethodError ex) {
                    SLF4JBridgeHandler.uninstall();
                }
            }
        };
    }
}
