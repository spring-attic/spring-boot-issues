package net.contargo.boot.autoconfigure;

import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.validation.annotation.Validated;


@Validated
@ConfigurationProperties("my.custom.management")
public class CustomManagementServerProperties extends ManagementServerProperties {

    static final String CUSTOM_PATH = "/__custom/management";

    public CustomManagementServerProperties() {

        setContextPath(CUSTOM_PATH);
    }
}
