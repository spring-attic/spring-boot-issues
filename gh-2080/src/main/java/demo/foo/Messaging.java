package demo.foo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.integration.annotation.MessagingGateway;

@ConditionalOnBean(name = "fooChannel")
@MessagingGateway(defaultRequestChannel = "fooChannel")
public interface Messaging { }
