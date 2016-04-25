package org.springframework.boot.test.mock.mockito;

import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockitoScopedProxyContextCustomizerFactory implements ContextCustomizerFactory {

    public ContextCustomizer createContextCustomizer(Class<?> testClass,
            List<ContextConfigurationAttributes> configAttributes) {
        DefinitionsParser parser = new DefinitionsParser();
        parser.parse(testClass);
        return new MockitoScopedProxyContextCustomizer(getMockAndSpyTypes(parser));
    }

    private static Set<Class> getMockAndSpyTypes(DefinitionsParser parser) {
        Set<Class> mockAndSpyTypes = new HashSet<>();

        for (Definition mockDef : parser.getDefinitions()) {
            if (mockDef instanceof MockDefinition) {
                mockAndSpyTypes.add(((MockDefinition) mockDef).getClassToMock());
            }
            if (mockDef instanceof SpyDefinition) {
                mockAndSpyTypes.add(((SpyDefinition) mockDef).getClassToSpy());
            }
        }

        return mockAndSpyTypes;
    }
}
