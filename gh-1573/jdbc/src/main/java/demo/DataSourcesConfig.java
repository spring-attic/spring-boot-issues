package demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourcesConfig {
  @Bean
  @ConfigurationProperties(prefix = "datasource.missing")
  public DataSource dataSource() {
    DataSource ds = DataSourceBuilder.create().build();
    System.out.println("Initialised DataSource: " + ds);
    return ds;
  }
}
