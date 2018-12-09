package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
    "com.example.my.server.port=58080" // override value on nested object property
    , "com.example.my.servers[0].port=50000" // override value in list element object property
})
public class ConfigurationPropertiesDemoApplicationTests {

  @Autowired
  private MyProperties properties;

  @Test
  public void contextLoads() {
    Assertions.assertThat(properties.getName()).isEqualTo("mock server");
    Assertions.assertThat(properties.getDescription()).isEqualTo("This is mock server.");
    Assertions.assertThat(properties.getServer().getName()).isEqualTo("web server"); // <- keep property value that specify in application.properties (it is expected behavior)
    Assertions.assertThat(properties.getServer().getPort()).isEqualTo(58080);
    Assertions.assertThat(properties.getServers().get(0).getName()).isEqualTo("tcp server"); // <- removed the value that specify in application.properties (it is not expected behavior)
    Assertions.assertThat(properties.getServers().get(0).getPort()).isEqualTo(50000);
  }

}
