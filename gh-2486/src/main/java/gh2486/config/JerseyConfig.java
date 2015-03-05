package gh2486.config;

import gh2486.resource.RootResource;
import gh2486.resource.TweetsStopWatchResource;
import gh2486.resource.TweetsProfiledResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(RootResource.class);
        register(TweetsStopWatchResource.class);
        register(TweetsProfiledResource.class);
    }
}