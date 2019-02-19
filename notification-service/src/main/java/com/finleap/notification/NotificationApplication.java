package com.finleap.notification;

import akka.actor.ActorSystem;
import com.finleap.notification.worker.WelcomeNotificationJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import static com.finleap.notification.container.SpringExtension.SPRING_EXTENSION_PROVIDER;

/**
 * The type Notification application.
 */
@SpringBootConfiguration
@SpringBootApplication
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class NotificationApplication {

	@Autowired
	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory.getLogger(NotificationApplication.class);


	/**
	 * Actor system actor system.
	 *
	 * @return the actor system
	 */
	@Bean
	public ActorSystem actorSystem() {
		ActorSystem system = ActorSystem.create("NotificationActor");
		SPRING_EXTENSION_PROVIDER.get(system).initialize(applicationContext);
		return system;
	}

	/**
	 * Rest template rest template.
	 *
	 * @param builder the builder
	 * @return the rest template
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		logger.info("Notification Service Application started............................");
		SpringApplication.run(NotificationApplication.class, args);
	}
}
