package app;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceTestConfiguration {

    public class Service {

        public void start() {
            System.out.println("Started!!!");
        }

        public void stop() {
            System.out.println("Stopped!!!");
        }

        public void test() {
            System.out.println("Test!!!");
        }
    }

    @Bean(destroyMethod = "stop")
    public Service createBean() {
        Service destroyTest = new Service();
        destroyTest.start();
        return destroyTest;
    }
}