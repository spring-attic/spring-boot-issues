/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.elrc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

import com.edlogics.common.theme.HostCookieThemeResolver;
import com.edlogics.common.theme.ReloadableResourceBundleThemeSource;

/**
 * Configure Spring themes.
 */
@Configuration
public class ThemeConfig {

	/**
	 * Themes are found in "resources/themes/*.properties".
	 *
	 * @return The theme source.
	 */
	@Bean
	public ResourceBundleThemeSource themeSource() {
		ResourceBundleThemeSource source = new ReloadableResourceBundleThemeSource();
		source.setBasenamePrefix( "themes/" );
		return source;
	}

	/**
	 * The param name to change a theme is "theme".
	 *
	 * @return The theme change interceptor.
	 */
	@Bean
	public ThemeChangeInterceptor themeChangeInterceptor() {
		ThemeChangeInterceptor themeChangeInterceptor = new ThemeChangeInterceptor();
		themeChangeInterceptor.setParamName( "theme" );
		return themeChangeInterceptor;
	}

	/**
	 * The default theme is "localhost".
	 *
	 * @return The theme resolver.
	 */
	@Bean
	public ThemeResolver themeResolver() {
		final HostCookieThemeResolver themeResolver = new HostCookieThemeResolver();
		themeResolver.setDefaultThemeName( "localhost" );
		return themeResolver;
	}
}
