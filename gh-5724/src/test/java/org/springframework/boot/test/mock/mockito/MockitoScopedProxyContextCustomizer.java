package org.springframework.boot.test.mock.mockito;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.MergedContextConfiguration;

import java.util.Set;

public class MockitoScopedProxyContextCustomizer implements ContextCustomizer {

    private final Set<Class> mockedTypes;

    public MockitoScopedProxyContextCustomizer(Set<Class> mockedTypes) {
        this.mockedTypes = mockedTypes;
    }

    public void customizeContext(ConfigurableApplicationContext context, MergedContextConfiguration mergedConfig) {
        MockitoScopedProxyPostProcessor.register((BeanDefinitionRegistry) context, mockedTypes);
    }
}