JSONP related issue in spring-boot-starter-jersey
=========================================================


In order to reproduce the issue simply start the application (main class : gh2486.Application) and make the following
API calls :

<pre><code>
$ curl -X GET http://localhost:8080/api/stopwatchtweets/1
callback({"id":1,"text":"hello there","createdAt":1425553533817})
$ curl -X GET http://localhost:8080/api/profiledtweets/1
callback({"id":1,"text":"hello there","createdAt":1425553537982})
</code></pre>

Both resources return valid JSONP content.

Relevant  logging output showing that the @Profiled annotation is not being taken into account since the class
gh2486.resource.TweetsProfiledResource is not a spring component :

<pre><code>
INFO 5933 --- [nio-8080-exec-1] org.perf4j.TimingLogger                  : start[1425552580772] time[4] tag[TweetsResourceWithComponent.readTweet][id:1]
</code></pre>


When adding the @Component annotation on the class gh2486.resource.TweetsProfiledResource the results will
look like in the following code block :

<pre><code>
$ curl -X GET http://localhost:8080/api/stopwatchtweets/1
callback({"id":1,"text":"hello there","createdAt":1425553397773})
$ curl -X GET http://localhost:8080/api/profiledtweets/1
{"id":1,"text":"hello there","createdAt":1425553405453}
</code></pre>

Notice that the call for http://localhost:8080/api/profiledtweets/1 resource doesn't return valid JSONP content.

Relevant logging output showing that @Profiled annotation is now being taken into account :
<pre><code>
INFO 6211 --- [nio-8080-exec-1] org.perf4j.TimingLogger                  : start[1425553029025] time[3] tag[TweetsStopWatchResource.readTweet][id:1]
INFO 6211 --- [nio-8080-exec-3] org.perf4j.TimingLogger                  : start[1425553031025] time[18] tag[TweetsProfiledResource.readTweet][id:1]
</code></pre>


