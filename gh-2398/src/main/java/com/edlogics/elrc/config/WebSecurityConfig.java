/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.elrc.config;

import java.security.SecureRandom;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.edlogics.common.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * Customizes Spring Security configuration.
 *
 * @author Chris Savory
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private ApplicationContext context;

	/**
	 * Want to make sure ApplicationSettings is configured first
	 */
	@Resource
	private ApplicationSettings applicationSettings;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(
	 * org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@SuppressWarnings("static-access")
	@Override
	protected void configure( HttpSecurity http ) throws Exception {

		// @formatter:off
		http
			.csrf()
				.requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")).disable()

			// See https://jira.springsource.org/browse/SPR-11496
			// For IE8 and IE9 SockJS
			.headers().addHeaderWriter(
				new XFrameOptionsHeaderWriter(
						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)).and()

			.formLogin()
                .usernameParameter("j_username") /* BY DEFAULT IS username!!! */
                .passwordParameter("j_password") /* BY DEFAULT IS password!!! */
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login.html")
                .failureUrl("/login.html?error")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
			.logout()
				.logoutSuccessUrl("/login?logout")
				.logoutUrl("/logout")
				.permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/less/**").permitAll()
				.antMatchers("/img/**").permitAll()
				.antMatchers("/fonts/**").permitAll()
				.antMatchers("/json/**").permitAll()
				.antMatchers("/docs/**").permitAll()
				.antMatchers("/sign-up/**").permitAll()
				.antMatchers("/join/**").permitAll()
				.antMatchers("/remote-user/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/registration/**").permitAll()
				.antMatchers("/profile-service/avatars").permitAll()
				.antMatchers("/account/send-password-reset").permitAll()
				.antMatchers("/account/reset-password/**").permitAll()
				.antMatchers("/legal/terms").permitAll()
				.antMatchers("/legal/privacy").permitAll()
				.antMatchers("/help").permitAll()
				.antMatchers("/rss").permitAll()
				.antMatchers("/health").permitAll()
				.anyRequest().authenticated()
				.and();
		// @formatter:on
	}

	@Override
	public void configure( WebSecurity web ) throws Exception {
		web.ignoring().antMatchers( "/oauth/uncache_approvals", "/oauth/cache_approvals" );
	}

	@Override
	@Autowired
	public void setApplicationContext( ApplicationContext context ) {
		super.setApplicationContext( context );
		this.context = context;
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder( 10 );
	}

	@SuppressWarnings("deprecation")
	@Bean(name = "tokenService")
	public TokenService getTokenService() {
		KeyBasedPersistenceTokenService tokenService = new KeyBasedPersistenceTokenService();
		tokenService.setServerSecret( "!3dL061c$#P7@tF0rW" );
		tokenService.setSecureRandom( new SecureRandom() );
		tokenService.setServerInteger( new Integer( 5178 ) );
		/*
		 * Produces a token key roughly of a length == 280 characters.
		 * 
		 * With a url length limitation of roughly 669 characters this is the maximum size token key to generate
		 * and still allow for 389 characters for the base url string and any additional parameters.
		 */
		tokenService.setPseudoRandomNumberBits( 32 );
		return tokenService;
	}
}