package example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class FooBarApplication {

  @Autowired FooBarService service;

  public static void main(String[] args) {
    SpringApplication.run(FooBarApplication.class, args);
  }

  @GetMapping("/foo")
  public String foo() {
    return service.foo();
  }

  @GetMapping("/bar")
  public void bar() {
    service.bar();
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public void handleException() {
    // do logging etc
  }

}
