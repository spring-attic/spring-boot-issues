package example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.mockito.Mockito.*;

/**
 * Here we create some default implementations for beans used in WebMvc context.
 */
@Configuration
public class TestConfiguration {

  @Bean
  public FooService fooService() {
    return mock(FooService.class);
  }

  @Bean
  public BarService barService() {
    return mock(BarService.class);
  }

}
