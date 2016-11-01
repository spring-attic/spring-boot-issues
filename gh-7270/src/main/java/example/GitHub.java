package example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "github", url = "https://api.github.com")
public interface GitHub {
  @RequestMapping(value = "/repos/{owner}/{repo}", method = RequestMethod.GET)
  Repo getRepository(@PathVariable(name = "owner") String owner, @PathVariable(name = "repo") String repo);
}
