package gh2486.config;

import org.perf4j.slf4j.aop.TimingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class Perf4jConfig {
    @Bean
    public TimingAspect timingAspect() {
        return new TimingAspect();
    }
}
