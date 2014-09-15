package org.springframework.boot.issues.gh1530;

import java.util.Locale;
import javax.servlet.Filter;
import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@Configuration
@ComponentScan(basePackages = "org.springframework.boot.issues.gh1530")
@EnableAutoConfiguration
@EnableTransactionManagement(order = Ordered.LOWEST_PRECEDENCE - 20)
@EnableAspectJAutoProxy
public class Application {

    public static void main(String[] args) {
        SpringApplication springApp = new SpringApplication(Application.class);
        springApp.run(args);
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.mine")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Do not help the @Ordered and setTransactionSynchronization settings...
     * @return 
     */
    @Bean
    @Primary
    @Order//(Ordered.LOWEST_PRECEDENCE - 10)
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager dm = new DataSourceTransactionManager(primaryDataSource());
        //dm.setTransactionSynchronization(Ordered.LOWEST_PRECEDENCE - 10);
        return dm;
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean
    public LocaleResolver localeResolver() {
        FixedLocaleResolver flr = new FixedLocaleResolver();
        flr.setDefaultLocale(new Locale("hu"));
        return flr;
    }

}
