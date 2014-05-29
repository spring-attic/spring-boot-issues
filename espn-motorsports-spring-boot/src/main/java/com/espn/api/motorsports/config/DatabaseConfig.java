package com.espn.api.motorsports.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DatabaseConfig {

   /* @Autowired
    private ConnectionSettings connectionSettings;*/
    
   /* @Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		 * dataSource.setDriverClassName(connectionSettings.getDriver());
		 * dataSource.setUrl(connectionSettings.getUrl());
		 * dataSource.setUsername(connectionSettings.getUsername());
		 * dataSource.setPassword(connectionSettings.getPassword());
		 
		dataSource
				.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource
				.setUrl("jdbc:sqlserver://VWBDDB01.corp.espn3.com;databaseName=SportWareMotorsportXO");
		dataSource.setUsername("psuser");
		dataSource.setPassword("Espn1234");
		return dataSource;
	}*/
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
//        lef.setDataSource(dataSource());
//        lef.setJpaVendorAdapter(jpaVendorAdapter());
//        lef.setPackagesToScan(new String[] { "com.espn.sports.persistence.jpa","com.espn.sports.persistence.motorsports.jpa" });
        lef.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
//        lef.setPersistenceUnitName("MSJpa");
        return lef;
    }
    
   /* @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.SQL_SERVER);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.SQLServerDialect");
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }*/

}