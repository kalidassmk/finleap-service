package com.finleap.notification;

import akka.actor.ActorSystem;
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

@SpringBootConfiguration
@SpringBootApplication
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class NotificationApplication {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public ActorSystem actorSystem() {
		ActorSystem system = ActorSystem.create("NotificationActor");
		SPRING_EXTENSION_PROVIDER.get(system).initialize(applicationContext);
		return system;
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}
}
