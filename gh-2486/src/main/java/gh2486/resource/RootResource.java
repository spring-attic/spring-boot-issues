package gh2486.resource;

import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;

@Component
@Path(RootResource.BASE_URL)
public class RootResource {
    public static final String BASE_URL = "/api";

    @Path("stopwatchtweets")
    public TweetsStopWatchResource stopwatchTweets(@Context ResourceContext resourceContext) {
        return resourceContext.getResource(TweetsStopWatchResource.class);
    }

    @Path("profiledtweets")
    public TweetsProfiledResource profiledTweets(@Context ResourceContext resourceContext) {
        return resourceContext.getResource(TweetsProfiledResource.class);
    }
}
