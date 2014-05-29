package com.espn.api.motorsports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import com.espn.sports.dao.motorsports.jpa.RacingTeamJpaDAOImpl;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import com.espn.api.motorsports.services.RacingTeamService;

@Configuration
public class XoverClientConfig  {
	
	@PersistenceUnit
    private EntityManagerFactory emf;
	
	@Bean
	public RacingTeamJpaDAOImpl createRacingTeamDao() {
		return new RacingTeamJpaDAOImpl(emf);
	}
	
//	@Singleton
	/*@PersistenceContext(name="MSJpa")
	EntityManagerFactory emf;// = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
*/	
	
	/*@Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
	      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { "com.espn.sports.persistence.jpa","com.espn.sports.persistence.motorsports.jpa" });
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	 
	      return em;
	   }
	 
	   @Bean
	   public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	      dataSource.setUrl("jdbc:sqlserver://VWBDDB01.corp.espn3.com;databaseName=SportWareMotorsportXO");
	      dataSource.setUsername( "psuser" );
	      dataSource.setPassword( "Espn1234" );
	      return dataSource;
	   }
	 
	   @Bean
	   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
	      JpaTransactionManager transactionManager = new JpaTransactionManager();
	      transactionManager.setEntityManagerFactory(emf);
	 
	      return transactionManager;
	   }
	 
	   @Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	      return new PersistenceExceptionTranslationPostProcessor();
	   }
	 
	   Properties additionalProperties() {
	      Properties properties = new Properties();
	      properties.setProperty("hibernate.show_sql", "true");
	      properties.setProperty("hibernate.format_sql", "true");
	      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
	      return properties;
	   }*/
	   
	
	
	/*@Bean
	public RacingCompetitionClient createRacingCompetitionClient() {
		return new RacingCompetitionClient();
	}
	
	@Bean
	public RacingEventSeasonClient createRacingSeasonClient() {
		return new RacingEventSeasonClient();
	}
	
	@Bean
	public RacingPersonSeasonRecordClient createRacingPersonSeasonStandingsClient() {
		return new RacingPersonSeasonRecordClient();
	}*/
}
