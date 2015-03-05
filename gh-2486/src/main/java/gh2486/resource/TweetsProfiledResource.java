package gh2486.resource;

import gh2486.contract.Tweet;
import org.glassfish.jersey.server.JSONP;
import org.perf4j.aop.Profiled;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Date;

//FIXME adding @Component annotation to this class for some reason breaks the JSONP functionality of Jersey
// not adding @Component requires explicit perf4j stopwatches because @Profiled annotations
// are not taken anymore into account
public class TweetsProfiledResource {

    @GET
    @Path("{id}")
    @JSONP(queryParam = "callback")
    @Produces({"application/x-javascript", "application/json"})
    @Profiled(tag = "TweetsProfiledResource.readTweet][id:{$0}")
    public Response readTweet(@PathParam("id") Long id) {
        Tweet tweet = new Tweet();
        tweet.setId(id);
        tweet.setCreatedAt(new Date());
        tweet.setText("hello there");
        return Response.ok(tweet).build();
    }

}
