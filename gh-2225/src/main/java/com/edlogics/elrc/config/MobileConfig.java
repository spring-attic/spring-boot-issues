/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.edlogics.elrc.config;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.mobile.device.view.LiteDeviceDelegatingViewResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Spring Mobile's {@link LiteDeviceDelegatingViewResolver}. If {@link ThymeleafViewResolver} is
 * available
 * it is configured as the delegate view resolver. Otherwise, {@link InternalResourceViewResolver} is used as a fallback.
 * 
 * @author Roy Clarkson
 * @since 1.1.0
 */
@Configuration
@ConditionalOnClass(LiteDeviceDelegatingViewResolver.class)
public class MobileConfig implements EnvironmentAware {

	@Resource(name = "thymeleafViewResolver")
	private ThymeleafViewResolver viewResolver;

	private RelaxedPropertyResolver environment;

	@Override
	public void setEnvironment( Environment environment ) {
		this.environment = new RelaxedPropertyResolver( environment,
				"spring.mobile.devicedelegatingviewresolver." );
	}

	@Bean(name = "deviceDelegatingViewResolver")
	protected CustomDeviceDelegatingViewResolver getConfiguredViewResolver() {
		CustomDeviceDelegatingViewResolver resolver = new CustomDeviceDelegatingViewResolver( viewResolver );
		resolver.setNormalPrefix( environment.getProperty( "normal-prefix", "" ) );
		resolver.setNormalSuffix( environment.getProperty( "normal-suffix", "" ) );
		resolver.setMobilePrefix( environment.getProperty( "mobile-prefix", "mobile/" ) );
		resolver.setMobileSuffix( environment.getProperty( "mobile-suffix", "" ) );
		resolver.setTabletPrefix( environment.getProperty( "tablet-prefix", "tablet/" ) );
		resolver.setTabletSuffix( environment.getProperty( "tablet-suffix", "" ) );
		resolver.setOrder( viewResolver.getOrder() == Ordered.HIGHEST_PRECEDENCE ? Ordered.HIGHEST_PRECEDENCE : viewResolver.getOrder() - 1 );
		resolver.setEnableFallback( true );
		return resolver;
	}

	public static class CustomDeviceDelegatingViewResolver extends LiteDeviceDelegatingViewResolver {

		/**
		 * @param delegate
		 */
		public CustomDeviceDelegatingViewResolver( ViewResolver delegate ) {
			super( delegate );
		}

		/**
		 * @param template
		 * @return
		 */
		public String getDeviceResolvedTemplate( String template ) {
			return getDeviceViewName( template );
		}

	}
}