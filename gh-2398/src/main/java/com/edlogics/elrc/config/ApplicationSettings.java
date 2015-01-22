/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.elrc.config;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Settings that are configured external to the application
 * Most likely these will come from application.yml
 *
 *
 * @author csavory
 *
 */
@Component
public class ApplicationSettings implements ApplicationContextAware {

	/**
	 * Used to access properties and profiles
	 */
	private static Environment environment;

	/**
	 * static reference so POJO's can reference the properties too
	 */
	public static ApplicationSettings SETTINGS;

	/**
	 * Autowired inner class that contains application media specific props
	 */
	@Resource
	protected ApplicationMediaProperties applicationMediaConfig;

	/**
	 * Autowired inner class that contains application specific props
	 */
	@Resource
	protected ApplicationProperties applicationConfig;

	@Value("${app.session-timeout}")
	private String timeoutMinutes;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext( ApplicationContext context ) throws BeansException {
		SETTINGS = context.getBean( ApplicationSettings.class );

		environment = context.getEnvironment();
	}

	/**
	 * @return the ApplicationMediaProperties object
	 */
	public ApplicationMediaProperties getApplicationMediaConfig() {
		return applicationMediaConfig;
	}

	/**
	 * @return the ApplicationMediaProperties object
	 */
	public ApplicationProperties getApplicationConfig() {
		return applicationConfig;
	}

	/**
	 * media specific application settings
	 */
	@Component
	@ConfigurationProperties("app.media")
	public static class ApplicationMediaProperties {

		private String urlRoot;

		private String avatarThumbDir;

		private String audioHead2headDir;

		private String badgesDir;

		private String emmiBaseUrl;

		/**
		 * @return the urlRoot
		 */
		public String getUrlRoot() {
			return urlRoot;
		}

		/**
		 * @param username the urlRoot to set
		 */
		public void setUrlRoot( String urlRoot ) {
			this.urlRoot = urlRoot;
		}

		/**
		 * @return the avatarThumbDir
		 */
		public String getAvatarThumbnailDirectory() {
			return avatarThumbDir;
		}

		/**
		 * @param avatarThumbDir the avatarThumbDir to set
		 */
		public void setAvatarThumbDir( String avatarThumbDir ) {
			this.avatarThumbDir = avatarThumbDir;
		}

		/**
		 * @return the audioHead2headDir
		 */
		public String getAudioHead2headDirectory() {
			return audioHead2headDir;
		}

		/**
		 * @param audioHead2headDir the audioHead2headDir to set
		 */
		public void setAudioHead2headDir( String audioHead2headDir ) {
			this.audioHead2headDir = audioHead2headDir;
		}

		/**
		 * @return the badgesDir
		 */
		public String getBadgesDirectory() {
			return badgesDir;
		}

		/**
		 * @param badgesDir the badgesDir to set
		 */
		public void setBadgesDir( String badgesDir ) {
			this.badgesDir = badgesDir;
		}

		/**
		 * @return
		 */
		public String getEmmiBaseUrl() {
			return emmiBaseUrl;
		}

		/**
		 * @param emmiBaseUrl
		 */
		public void setEmmiBaseUrl( String emmiBaseUrl ) {
			this.emmiBaseUrl = emmiBaseUrl;
		}
	}

	/**
	 * application settings
	 */
	@Component
	@ConfigurationProperties("app")
	public static class ApplicationProperties {

		private String baseUrl;
		private String googleAnalyticsTrackingId;

		/**
		 * @return the urlRoot
		 */
		public String getBaseUrl() {
			return baseUrl;
		}

		/**
		 * @param baseUrl
		 * set the parameter passed in
		 */
		public void setBaseUrl( String baseUrl ) {
			this.baseUrl = baseUrl;
		}

		/**
		 * @return the googleAnalyticsTrackingId
		 */
		public String getGoogleAnalyticsTrackingId() {
			return googleAnalyticsTrackingId;
		}

		/**
		 * @param googleAnalyticsTrackingId
		 * set the parameter passed in
		 */
		public void setGoogleAnalyticsTrackingId(String googleAnalyticsTrackingId) {
			this.googleAnalyticsTrackingId = googleAnalyticsTrackingId;
		}
		
	}

	public static boolean isTest() {
		return environment.acceptsProfiles( "test" );
	}

	public static boolean isLocal() {
		return environment.acceptsProfiles( "local" );
	}

	public static boolean isLatest() {
		return environment.acceptsProfiles( "latest" );
	}

	public static boolean isStage() {
		return environment.acceptsProfiles( "stage" );
	}

	public static boolean isDemo() {
		return environment.acceptsProfiles( "demo" );
	}

	public static boolean isProduction() {
		return environment.acceptsProfiles( "production" );
	}

	public static String[] getActiveProfiles() {
		return environment.getActiveProfiles();
	}

	/**
	 * @return the timeoutMinutes
	 */
	public String getTimeoutMinutes() {
		return timeoutMinutes;
	}

	/**
	 * @param timeoutMinutes the timeoutMinutes to set
	 */
	public void setTimeoutMinutes( String timeoutMinutes ) {
		this.timeoutMinutes = timeoutMinutes;
	}
}