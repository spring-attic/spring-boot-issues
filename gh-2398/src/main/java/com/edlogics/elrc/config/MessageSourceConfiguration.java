/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.elrc.config;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Environment specific configuration for the CustomResourceBundle.
 * See below for comments on that class
 *
 * @author csavory
 *
 */
@Configuration
public class MessageSourceConfiguration implements EnvironmentAware {

	private RelaxedPropertyResolver environment;

	@Override
	public void setEnvironment( Environment environment ) {
		this.environment = new RelaxedPropertyResolver( environment, "spring.messages." );
	}

	@Bean
	public MessageSource messageSource() {

		boolean useReloadableMessageSource = Boolean.valueOf( this.environment.getProperty( "reloadable", "false" ) );
		String[] baseNames = StringUtils.commaDelimitedListToStringArray( this.environment.getProperty( "basename", "messages" ) );
		String encoding = this.environment.getProperty( "encoding", "utf-8" );
		String cacheSeconds = this.environment.getProperty( "cache-seconds", "60" );

		if ( useReloadableMessageSource ) {
			CustomReloadableResourceBundle messageSource = new CustomReloadableResourceBundle();
			messageSource.setBasenames( baseNames );
			messageSource.setDefaultEncoding( encoding );
			messageSource.setCacheSeconds( Integer.valueOf( cacheSeconds ) );
			messageSource.setFallbackToSystemLocale( false );
			return messageSource;
		} else {
			CustomResourceBundle messageSource = new CustomResourceBundle();
			messageSource.setBasenames( baseNames );
			messageSource.setDefaultEncoding( encoding );
			messageSource.setFallbackToSystemLocale( false );
			return messageSource;

		}
	}

	/**
	 * An extension of ResourceBundleMessageSource that provides all messages. All Messages are cached.
	 *
	 */
	public static class CustomResourceBundle extends ResourceBundleMessageSource {

		public static final String ALL_MESSASGES = "all-messages-";
		private Set<String> baseNames;
		private Map<String, Properties> cachedData = new HashMap<String, Properties>();
		private final ObjectMapper mapper = new ObjectMapper();

		public CustomResourceBundle() {
			super();
		}

		public Properties getMessagesForBasename( String basename, Locale locale ) {
			String cacheKey = basename + locale.getCountry() + "+" + locale.getLanguage();
			if ( cachedData.containsKey( cacheKey ) ) {
				return cachedData.get( cacheKey );
			}

			ResourceBundle bundle = getResourceBundle( basename, locale );
			Properties properties = convertResourceBundleToProperties( bundle );
			cachedData.put( cacheKey, properties );
			return properties;
		}

		public Properties getAllMessages( Locale locale ) {
			String cacheKey = ALL_MESSASGES + locale.getCountry() + "+" + locale.getLanguage();
			if ( cachedData.containsKey( cacheKey ) ) {
				return cachedData.get( cacheKey );
			}

			Properties properties = new Properties();
			for ( String baseName : baseNames ) {
				properties.putAll( getMessagesForBasename( baseName, locale ) );
			}
			cachedData.put( cacheKey, properties );
			return properties;
		}

		public Properties getMessages( Locale locale, String... propertyNames ) {
			Properties filteredProperties = new Properties();
			Properties allProperties = getAllMessages( locale );

			for ( String key : propertyNames ) {
				if ( allProperties.containsKey( key ) ) {
					filteredProperties.put( key, allProperties.getProperty( key ) );
				}
			}

			return filteredProperties;
		}

		public String getMessagesAsJsonString( Locale locale ) throws JsonGenerationException, JsonMappingException, IOException {
			return mapper.writeValueAsString( getAllMessages( locale ) );
		}

		@Override
		public void setBasenames( String... basenames ) {
			baseNames = new HashSet<String>();
			if ( basenames != null ) {
				for ( int i = 0; i < basenames.length; i++ ) {
					String basename = basenames[i];
					Assert.hasText( basename, "Basename must not be empty" );
					baseNames.add( basename.trim() );
				}
			}

			super.setBasenames( basenames );
		}

		/**
		 * Convert ResourceBundle into a Properties object.
		 *
		 * @param resource a resource bundle to convert.
		 * @return Properties a properties version of the resource bundle.
		 */
		private static Properties convertResourceBundleToProperties( ResourceBundle resource ) {
			Properties properties = new Properties();

			Enumeration<String> keys = resource.getKeys();
			while ( keys.hasMoreElements() ) {
				String key = keys.nextElement();
				properties.put( key, resource.getString( key ) );
			}

			return properties;
		}
	}

	/**
	 * An extension of ReloadableResourceBundleMessageSource that provides all messages.
	 *
	 */
	public static class CustomReloadableResourceBundle extends ReloadableResourceBundleMessageSource {

		private final ObjectMapper mapper = new ObjectMapper();

		public CustomReloadableResourceBundle() {
			super();
		}

		public Properties getAllMessages( Locale locale ) {
			PropertiesHolder propertiesHolder = getMergedProperties( locale );
			Properties properties = propertiesHolder.getProperties();

			return properties;
		}

		public Properties getMessages( Locale locale, String... propertyNames ) {
			Properties filteredProperties = new Properties();
			Properties allProperties = getAllMessages( locale );

			for ( String key : propertyNames ) {
				if ( allProperties.containsKey( key ) ) {
					filteredProperties.put( key, allProperties.getProperty( key ) );
				}
			}

			return filteredProperties;
		}

		public String getMessagesAsJsonString( Locale locale ) throws JsonGenerationException, JsonMappingException, IOException {
			return mapper.writeValueAsString( getAllMessages( locale ) );
		}

		@Override
		protected PropertiesHolder getProperties( String filename ) {
			return super.getProperties( "classpath:/" + filename );
		}
	}
}
