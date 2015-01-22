package com.edlogics.elrc.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.db.DBAppender;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.core.db.DataSourceConnectionSource;
import ch.qos.logback.core.spi.FilterReply;

@Configuration
@Profile("!test")
public class LoggingConfig {

	@Resource
	private DataSource dataSource;

	@Bean
	public Logger logger() {

		Logger logger = (Logger) LoggerFactory.getLogger( Logger.ROOT_LOGGER_NAME );
		LevelFilter filter = new LevelFilter();
		filter.setLevel( Level.ERROR );
		filter.setOnMatch( FilterReply.ACCEPT );
		filter.setOnMismatch( FilterReply.DENY );

		DataSourceConnectionSource connSource = new DataSourceConnectionSource();
		connSource.setDataSource( dataSource );
		connSource.setContext( logger.getLoggerContext() );
		connSource.start();

		DBAppender dbAppender = new DBAppender();
		dbAppender.setName( "DB" );
		dbAppender.setConnectionSource( connSource );
		dbAppender.setContext( logger.getLoggerContext() );
		dbAppender.addFilter( filter );
		dbAppender.start();

		logger.setLevel( Level.ERROR );
		logger.addAppender( dbAppender );

		return logger;
	}
}