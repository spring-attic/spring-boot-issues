package org.springframework.boot.test.mock.mockito;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;

import java.util.LinkedHashSet;
import java.util.Set;

public class MockitoScopedProxyPostProcessor implements BeanFactoryPostProcessor, Ordered {

    private static final String BEAN_NAME = MockitoScopedProxyPostProcessor.class.getName();
    public static final String SCOPED_TARGET_PREFIX = "scopedTarget.";

    private final Set<Class> mockedTypes;

    public MockitoScopedProxyPostProcessor(Set<Class> mockedTypes) {
        this.mockedTypes = mockedTypes;
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry bdr = (BeanDefinitionRegistry) beanFactory;

        for (Class mockedType : mockedTypes) {
            String[] mockedBeans = beanFactory.getBeanNamesForType(mockedType);

            for (String mockedBean : mockedBeans) {
                if (isScopedProxy(mockedBean)) {
                    bdr.removeBeanDefinition(mockedBean);
                }
            }
        }
    }

    private static boolean isScopedProxy(String mockedBean) {
        return mockedBean.startsWith(SCOPED_TARGET_PREFIX);
    }

    public static void register(BeanDefinitionRegistry registry, Set<Class> mockedTypes) {
        BeanDefinition definition = getOrAddBeanDefinition(registry);

        if (mockedTypes != null) {
            getConstructorArgs(definition).addAll(mockedTypes);
        }
    }

    @SuppressWarnings("unchecked")
    private static Set<Class> getConstructorArgs(BeanDefinition definition) {
        ConstructorArgumentValues.ValueHolder constructorArg = definition.getConstructorArgumentValues()
                .getIndexedArgumentValue(0, Set.class);
        return (Set<Class>) constructorArg.getValue();
    }

    private static BeanDefinition getOrAddBeanDefinition(BeanDefinitionRegistry registry) {
        if (!registry.containsBeanDefinition(BEAN_NAME)) {
            addBeanDefinition(registry);
        }

        return registry.getBeanDefinition(BEAN_NAME);
    }

    private static void addBeanDefinition(BeanDefinitionRegistry registry) {
        RootBeanDefinition def = new RootBeanDefinition(MockitoScopedProxyPostProcessor.class);
        def.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        ConstructorArgumentValues constructorArguments = def.getConstructorArgumentValues();
        constructorArguments.addIndexedArgumentValue(0, new LinkedHashSet<Class>());
        registry.registerBeanDefinition(BEAN_NAME, def);
    }

    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}