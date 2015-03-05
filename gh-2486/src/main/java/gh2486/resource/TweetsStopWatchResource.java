package gh2486.resource;

import gh2486.contract.Tweet;
import org.glassfish.jersey.server.JSONP;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Date;

//FIXME adding @Component for some reason breaks the JSONP functionality of Jersey
// not adding @Component requires explicit perf4j stopwatches because @Profiled annotations
// are not taken anymore into account

@Component
public class TweetsStopWatchResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(TweetsStopWatchResource.class);

    @GET
    @Path("{id}")
    @JSONP(queryParam = "callback")
    @Produces({"application/x-javascript", "application/json"})
    public Response readTweet(@PathParam("id") Long id) {
        Slf4JStopWatch stopWatch = new Slf4JStopWatch(getClass().getSimpleName() +
                ".readTweet][id:" + id);

        try {
            Tweet tweet = new Tweet();
            tweet.setId(id);
            tweet.setCreatedAt(new Date());
            tweet.setText("hello there");
            return Response.ok(tweet).build();
        } finally {
            stopWatch.stop();
        }
    }
}
