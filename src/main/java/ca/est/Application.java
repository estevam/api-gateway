package ca.est;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.config.EnableWebFlux;
/**
 * @author Estevam Meneses
 */
@EnableWebFlux
@EnableDiscoveryClient
@SpringBootApplication
@PropertySource("classpath:config.properties")
public class Application{
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
