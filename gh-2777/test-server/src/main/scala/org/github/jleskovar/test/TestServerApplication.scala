package org.github.jleskovar.test

import org.springframework.boot.SpringApplication
import org.springframework.boot.actuate.metrics.repository.MetricRepository
import org.springframework.boot.actuate.metrics.repository.redis.RedisMetricRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory

@SpringBootApplication
class TestServerApplication {

  @Bean
  def metricRepository(redisConnectionFactory: RedisConnectionFactory): MetricRepository =
    new RedisMetricRepository(redisConnectionFactory)

}

object TestServerApplication extends App {
  SpringApplication.run(classOf[TestServerApplication])
}
