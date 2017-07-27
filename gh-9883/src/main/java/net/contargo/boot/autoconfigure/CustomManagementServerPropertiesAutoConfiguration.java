package net.contargo.boot.autoconfigure;

import org.springframework.boot.actuate.autoconfigure.ManagementServerPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AutoConfigureBefore(ManagementServerPropertiesAutoConfiguration.class)
public class CustomManagementServerPropertiesAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(CustomManagementServerProperties.class)
    public CustomManagementServerProperties managementServerProperties() {

        return new CustomManagementServerProperties();
    }
}
