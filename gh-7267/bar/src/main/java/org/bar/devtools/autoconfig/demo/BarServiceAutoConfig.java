package org.bar.devtools.autoconfig.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * @author zaunerm
 */
@Configuration
public class BarServiceAutoConfig {
    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    @ConditionalOnMissingBean
    BarServiceImpl barService() {
        return new BarServiceImpl();
    }
}
