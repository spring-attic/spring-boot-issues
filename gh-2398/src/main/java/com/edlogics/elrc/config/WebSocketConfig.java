/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.elrc.config;

import java.util.concurrent.Executor;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.AbstractSubscribableChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.ExecutorSubscribableChannel;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurationSupport;

/**
 * Customizes Spring WebSocket configuration.
 *
 * @author Christopher Savory
 *
 */
@Configuration
@EnableScheduling
public class WebSocketConfig extends WebSocketMessageBrokerConfigurationSupport {

	@Resource
	private ServletContext servletContext;

	protected Logger logger = LoggerFactory.getLogger( getClass() );

	@Override
	@Bean
	public AbstractSubscribableChannel clientInboundChannel() {
		Executor securityExecutor = new DelegatingSecurityContextExecutor( clientInboundChannelExecutor() );
		ExecutorSubscribableChannel result = new ExecutorSubscribableChannel( securityExecutor );
		result.addInterceptor( securityContextChannelInterceptorAdapter() );
		return result;
	}

	@Bean
	public ChannelInterceptorAdapter securityContextChannelInterceptorAdapter() {
		return new ChannelInterceptorAdapter() {

			@Override
			public Message<?> preSend( Message<?> message, MessageChannel channel ) {
				Authentication auth = (Authentication) SimpMessageHeaderAccessor.getUser( message.getHeaders() );
				SecurityContextHolder.getContext().setAuthentication( auth );
				return super.preSend( message, channel );
			}

			@Override
			public void postSend( Message<?> message, MessageChannel channel, boolean sent ) {
				//SecurityContextHolder.clearContext();
				super.postSend( message, channel, sent );
			}
		};
	}

	@Override
	public void registerStompEndpoints( StompEndpointRegistry registry ) {
		registry.addEndpoint( "/head2head" ).withSockJS().setClientLibraryUrl( servletContext.getContextPath() + "/js/lib/sockjs/sockjs.min.js" );
	}

	@Override
	public void configureMessageBroker( MessageBrokerRegistry registry ) {
		registry.enableSimpleBroker( "/queue/" );
		registry.setApplicationDestinationPrefixes( "/app" );
	}

}