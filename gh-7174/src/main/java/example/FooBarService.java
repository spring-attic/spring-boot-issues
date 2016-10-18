package example;

import org.springframework.stereotype.Service;

@Service
public class FooBarService {

  public String foo() {
    return "foo";
  }

  public void bar() {
    // do some bar stuff
  }

}
