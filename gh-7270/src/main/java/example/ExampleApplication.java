package example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@EnableFeignClients
public class ExampleApplication {

  @Autowired RepositoryService service;

  public static void main(String[] args) {
    SpringApplication.run(ExampleApplication.class, args);
  }

  @GetMapping("/repo/{owner}/{repo}/desc")
  public String description(@PathVariable String owner, @PathVariable String repo) {
    return service.description(owner, repo);
  }
}
