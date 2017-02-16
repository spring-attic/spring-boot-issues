/*
 * Copyright 2002-2012 the original author or authors.
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

package com.edlogics.common.theme;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.ui.context.support.ResourceBundleThemeSource;

public class ReloadableResourceBundleThemeSource extends ResourceBundleThemeSource implements EnvironmentAware {

	private RelaxedPropertyResolver environment;

	@Override
	public void setEnvironment( Environment environment ) {
		this.environment = new RelaxedPropertyResolver( environment, "spring.messages." );
	}

	@Override
	protected MessageSource createMessageSource( String basename ) {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename( basename );
		String encoding = this.environment.getProperty( "encoding", "utf-8" );
		messageSource.setDefaultEncoding( encoding );
		String cacheSeconds = this.environment.getProperty( "cache-seconds", "60" );
		messageSource.setCacheSeconds( Integer.valueOf( cacheSeconds ) );
		messageSource.setFallbackToSystemLocale( false );
		return messageSource;
	}
}