package org.springframework.boot.issues.spring.config;

import org.springframework.boot.issues.AbstractSingleton;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.mbeans.JmxRemoteLifecycleListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig extends AbstractSingleton implements EmbeddedServletContainerCustomizer {

    @Value("${tomcat.proxyPort}")
    private Integer proxyPort;

    @Value("${tomcat.proxyName}")
    private String proxyName;

    @Value("${tomcat.scheme}")
    private String scheme;

    @Value("${tomcat.jmx.rmiServerPort}")
    private Integer rmiServerPort;

    @Value("${tomcat.jmx.rmiRegistryPort}")
    private Integer rmiRegistryPort;

    @Value("${tomcat.jmx.useLocalPorts}")
    private Boolean useLocalPorts;


    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return this;
    }

    // Not sure why this is so crazy...
    // http://docs.spring.io/spring-boot/docs/1.0.1.RELEASE/reference/htmlsingle/#howto-configure-tomcat

    // I simplified it by making TomcatConfig implement EmbeddedServletContainerCustomizer.
    // This could be moved to a common "px" jar


    @Override
    public void customize(ConfigurableEmbeddedServletContainer factory) {


        if (factory instanceof TomcatEmbeddedServletContainerFactory) {
            customize((TomcatEmbeddedServletContainerFactory) factory);
        } else {
            logger.warn("customize() expected TomcatEmbeddedServletContainerFactory but was: " + factory.getClass());
        }
    }

    private void customize(TomcatEmbeddedServletContainerFactory factory) {

        factory.addContextLifecycleListeners(jmxRemoteLifecycleListener());

        factory.addConnectorCustomizers(tomcatConnectorCustomizer());
    }

    private LifecycleListener jmxRemoteLifecycleListener() {

        JmxRemoteLifecycleListener lifecycleListener = new JmxRemoteLifecycleListener();

        lifecycleListener.setRmiServerPortPlatform(rmiServerPort);
        lifecycleListener.setRmiRegistryPortPlatform(rmiRegistryPort);
        lifecycleListener.setUseLocalPorts(useLocalPorts);

        return lifecycleListener;
    }

    private TomcatConnectorCustomizer tomcatConnectorCustomizer() {

        return new TomcatConnectorCustomizer() {

            @Override
            public void customize(Connector connector) {

                connector.setProxyPort(proxyPort);
                connector.setProxyName(proxyName);
                connector.setScheme(scheme);
            }
        };
    }
}
