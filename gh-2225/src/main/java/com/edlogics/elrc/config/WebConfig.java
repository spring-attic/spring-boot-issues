/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.elrc.config;

import java.net.MalformedURLException;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.mobile.device.DeviceResolver;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.extras.conditionalcomments.dialect.ConditionalCommentsDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.AjaxThymeleafViewResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * @author csavory
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements EnvironmentAware {

	@Resource
	MessageSource messages;

	@Autowired
	SpringTemplateEngine springTemplateEngine;

	private RelaxedPropertyResolver environment;

	@Override
	public void setEnvironment( Environment environment ) {
		this.environment = new RelaxedPropertyResolver( environment,
				"spring.mobile.devicedelegatingviewresolver." );
	}

	@Override
	public void addInterceptors( InterceptorRegistry registry ) {

		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName( "lang" );
		registry.addInterceptor( localeChangeInterceptor );
	}

	@Bean
	public LocaleResolver localeResolver() {

		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale( StringUtils
				.parseLocaleString( "en" ) );
		return cookieLocaleResolver;
	}

	/**
	 * Needed to enable validation at the method level using Bean and Hibernate
	 * Validator.
	 *
	 * @return
	 */
	@Bean
	public MethodValidationPostProcessor getMethodValidationPostProcessor(
			MessageSource messageSource ) {
		MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
		methodValidationPostProcessor
				.setValidatorFactory( getLocalValidatorFactoryBean( messageSource ) );
		return methodValidationPostProcessor;
	}

	/**
	 * The basic configuration above will trigger JSR-303 to initialize using
	 * its default bootstrap mechanism. A JSR-303 provider, such as Hibernate
	 * Validator, is expected to be present in the classpath and will be
	 * detected automatically.
	 *
	 * @return
	 */
	@Bean(name = "validator")
	public LocalValidatorFactoryBean getLocalValidatorFactoryBean(
			MessageSource messageSource ) {
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setValidationMessageSource( messageSource );
		return validatorFactoryBean;
	}

	@Bean(name = "restTemplate")
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Override
	public Validator getValidator() {
		return getLocalValidatorFactoryBean( messages );
	}

	@Bean(name = "deviceResolver")
	protected DeviceResolver getDeviceResolver() {
		return new LiteDeviceResolver();
	}

	@Bean(name = "thymeleafViewResolver")
	protected ThymeleafViewResolver getViewResolver() {

		// Get new ViewResolver
		ThymeleafViewResolver resolver = new CustomAjaxThymeleafViewResolver();
		resolver.setTemplateEngine( springTemplateEngine );

		// Setup encoding
		String encoding = this.environment.getProperty( "encoding", "UTF-8" );
		String type = this.environment.getProperty( "contentType", "text/html" );
		if ( !type.contains( encoding ) )
			type = type + ";charset=" + encoding;
		resolver.setCharacterEncoding( encoding );
		resolver.setContentType( type );

		// Set names
		resolver.setExcludedViewNames( this.environment.getProperty( "excludedViewNames", String[].class ) );
		resolver.setViewNames( this.environment.getProperty( "viewNames", String[].class ) );

		// This resolver acts as a fallback resolver (e.g. like a
		// InternalResourceViewResolver) so it needs to have low precedence
		resolver.setOrder( Ordered.LOWEST_PRECEDENCE - 5 );

		return resolver;
	}

	public static class CustomAjaxThymeleafViewResolver extends AjaxThymeleafViewResolver {

		@Value("${spring.mobile.thymeleaf.cache-fallback-result:true}")
		public Boolean bCache;

		@Autowired
		private TemplateResolver templateResolver;

		@Autowired
		private SpringTemplateEngine templateEngine;

		@Override
		public boolean isCache() {
			return bCache;
		}

		/**
		 * Override to actually look for the resource to see if it
		 * exists. This view resolver caches its views after the first time, so
		 * the overhead of this check may be acceptible.
		 */
		@Override
		protected boolean canHandle( final String viewName, final Locale locale ) {
			Boolean exists = thymeleafViewExists( viewName );
			return ( exists == null || exists ) ? super.canHandle( viewName, locale ) : exists;
		}

		protected Boolean thymeleafViewExists( String viewName ) {

			// Ensure that the Engine/Resolver is initialized
			templateEngine.initialize();

			org.springframework.core.io.Resource res;
			String viewPath = templateResolver.getPrefix() + viewName + templateResolver.getSuffix();

			if ( viewPath.startsWith( "classpath:" ) )
				res = new ClassPathResource( viewPath.substring( 10 ) );
			else if ( viewPath.startsWith( "file:" ) )
				res = new FileSystemResource( viewPath.substring( 5 ) );
			else {
				try {
					res = new UrlResource( viewPath );
				} catch ( MalformedURLException e ) {
					logger.info( "Unrecognised resource " + viewName );
					return null;   // Can't decide, give up 
				}
			}

			return res.exists();
		}
	};

	@Bean
	public ConditionalCommentsDialect conditionalCommentsDialect() {
		return new ConditionalCommentsDialect();
	}
}