/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics;

import static org.springframework.util.StringUtils.commaDelimitedListToStringArray;
import static org.springframework.util.StringUtils.trimAllWhitespace;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/**
 * This is the main class for Spring Boot to do the application startup and configuration
 *
 * @author Christopher Savory
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableJpaAuditing
@EnableEntityLinks
@EnableAsync
@EnableHypermediaSupport(type = HypermediaType.HAL)
@ComponentScan
public class ElrcApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

	protected Logger logger = LoggerFactory.getLogger( getClass() );

	/**
	 * Holds Spring Boot configuration properties
	 */
	protected Properties props = new Properties();

	@Value("${app.session-timeout}")
	private String sessionTimeout;

	@Value("${app.timezone}")
	String elrcTimezone;

	public ElrcApplication() {}

	/**
	 * Overrides the default configuration for the JSON Serialization on Spring REST
	 * Turns off the default date formatting
	 *
	 * @return the customized ObjectMapper
	 */
	@Bean
	@Primary
	public ObjectMapper objectMapper()
	{
		ObjectMapper om = new ObjectMapper();
		om.configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );
		return om;
	}

	/**
	 * Joda Module as a Bean. Spring will register this module with all ObjectMapper instances
	 * 
	 * @return
	 */
	@Bean
	public Module jodaModule() {
		return new JodaModule();
	}

	/**
	 * @return the Scheduler used
	 */
	@Bean
	public ThreadPoolTaskScheduler scheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize( 10 );
		return scheduler;
	}

	/**
	 * Bean to hold the system maintenance date time. Defaults to a far future date time.
	 * 
	 * @return
	 */
	@Bean
	public MutableDateTime maintenanceDateTime() {
		MutableDateTime farFuture = MutableDateTime.now( DateTimeZone.forID( elrcTimezone ) );
		// Set to year 9999 to make easy to check for far future value
		// There is no easy way to do a max Date with Joda time so this is the workaround
		farFuture.setYear( 9999 );
		return farFuture;
	}

	/**
	 * If the application is currently in maintenance as specified by the maintenance date time being set.
	 * 
	 * @return Is the current date time > maintenance date time?
	 */
	@Bean
	@Scope(WebApplicationContext.SCOPE_REQUEST)
	public boolean inMaintenance() {
		DateTime currentDateTime = DateTime.now( DateTimeZone.forID( elrcTimezone ) );
		return ( currentDateTime.getMillis() - maintenanceDateTime().getMillis() ) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.web.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure( SpringApplicationBuilder application ) {
		application.sources( ElrcApplication.class );

		// Set active profiles.
		List<String> profiles = new ArrayList<String>();

		String hostName = getHostname();

		if ( StringUtils.isNotBlank( System.getProperty( "spring.profiles.active" ) ) ) {
			for ( String profile : commaDelimitedListToStringArray( trimAllWhitespace( System.getProperty( "spring.profiles.active" ) ) ) ) {
				profiles.add( profile );
			}
		} else if ( "christohersmbp2".equals( hostName ) || "Christophers-MacBook-Pro-2.local".equals( hostName ) ) {
			profiles.add( "local" );
			profiles.add( "chris" );
		} else if ( "erics-mbp.home".equals( hostName ) || "Erics-MacBook-Pro.local".equals( hostName ) || "Erics-MBP.home".equals( hostName ) ) {
			profiles.add( "local" );
			profiles.add( "eric" );
		} else if ( "James".equals( hostName ) ) {
			profiles.add( "local" );
			profiles.add( "james" );
		} else if ( "pc-main".equals( hostName ) ) {
			profiles.add( "local" );
			profiles.add( "james" );
		} else if ( "kevins-iMac.home".equals( hostName ) ) {
			profiles.add( "local" );
			profiles.add( "kevin" );
		} else if ( "Jackson-THINK".equals( hostName ) ) {
			profiles.add( "local" );
			profiles.add( "david" );
		} else if ( "endor".equals( hostName ) || "endor.local".equals( hostName ) ) {
			profiles.add( "local" );
			profiles.add( "jeff" );
		} else if ( "ALIEN".equalsIgnoreCase( hostName ) ) {
			profiles.add( "local" );
			profiles.add( "alien" );
		} else if ( "BhaskarReddy".equals( hostName ) ) {
			profiles.add( "local" );
			profiles.add( "bhaskar" );
		} else if ( "Jasons-MacBook-Pro.local".equalsIgnoreCase( hostName ) || "alien2".equalsIgnoreCase( hostName ) ) {
			profiles.add( "local" );
			profiles.add( "alien2" );
		} else if ( "edlogics".equalsIgnoreCase( hostName ) ) {
			profiles.add( "local" );
			profiles.add( "edlogics-vm" );
		} else if ( "Banashley-pc".equalsIgnoreCase( hostName ) ) {
			profiles.add( "local" );
			profiles.add( "ben" );
		}

		logger.info( "Spring Boot configuration: profiles = " + profiles );
		application.profiles( profiles.toArray( new String[profiles.size()] ) );

		// Set additional properties. Note: this API does not exist in 0.5.0.M5
		// or earlier.
		logger.info( "Spring Boot configuration: properties = " + props );
		application.properties( props ); // New API

		return application;
	}

	@Override
	public void customize( ConfigurableEmbeddedServletContainer container ) {
		MimeMappings mappings = new MimeMappings( MimeMappings.DEFAULT );
		mappings.add( "ico", "image/vnd.microsoft.icon" );
		mappings.add( "woff", "application/font-woff" );
		mappings.add( "ttf", "application/octet-stream" );
		container.setMimeMappings( mappings );

		/*
		 * Establishes a time limit / time out for each users session.
		 * 
		 * Setting the key in the application.yml server.session-timeout : 15 does not accurately
		 * set the time limit / time out for each users session so it was removed.
		 * 
		 * There seems to be an inconsistency with setting the aforementioned value and then the value
		 * of session.getMaxInterval(). When setting the server.session-timeout : 15 the value of session.getMaxInterval()
		 * is not set to the same value. From our observation the value of session.getMaxInterval() seems to be the
		 * result of a calculation derived from server.session-timeout : 15 but we are not able to track down where that occurs.
		 * 
		 * There is a Jira issue open with Oracle to resolve setting a session time out similar to how you used to be able
		 * to set in prior to Servlet Spec 3.0. You can find the issue here: https://java.net/jira/browse/SERVLET_SPEC-70
		 */
		if ( StringUtils.isNotEmpty( sessionTimeout ) && container instanceof TomcatEmbeddedServletContainerFactory ) {
			TomcatEmbeddedServletContainerFactory factory = (TomcatEmbeddedServletContainerFactory) container;
			factory.setSessionTimeout( Integer.valueOf( sessionTimeout ), TimeUnit.MINUTES );
		}
	}

	/**
	 * This is called when running locally as Spring Boot, but not from Tomcat
	 *
	 * @param args
	 */
	public static void main( String[] args ) {

		System.setProperty( "jsse.enableSNIExtension", "false" );

		// Create an instance and invoke run(); Allows the constructor to perform
		// Initialization regardless of whether we are running as an application
		// or in a container.
		new ElrcApplication().runAsJavaApplication( args );
	}

	/**
	 * Run the application using Spring Boot. <code>SpringApplication.run</code> tells Spring Boot to use this class as the initialiser for the whole
	 * application (via the class annotations above). This method is only used
	 * when running as a Java application.
	 *
	 * @param args
	 *            Any command line arguments.
	 */
	protected void runAsJavaApplication( String[] args ) {
		SpringApplicationBuilder application = new SpringApplicationBuilder();
		configure( application );

		//Start the Server
		application.run( args );
	}

	/**
	 * get the host's hostname
	 * return hostname
	 */
	protected String getHostname() {
		String hostname = null;
		try {
			hostname = InetAddress.getLocalHost().getHostName();
		} catch ( UnknownHostException e ) {
			e.printStackTrace();
		}

		return hostname;
	}
}