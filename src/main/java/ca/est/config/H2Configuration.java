package ca.est.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import io.r2dbc.spi.ConnectionFactory;
/**
 * Create H2 tables and insert rows
 * @author Estevam Meneses
 * Dec. 29, 2021
 */
@Configuration
public class H2Configuration {

	@Value("${h2.schema}")
	private String schema;
	
	@Value("${h2.data}")
	private String data;
	
	/**
	 * ConnectionFactoryInitializer
	 * Create table 'endpoint' and add rows.
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	ConnectionFactoryInitializer databaseInitializer(ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
		populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource(schema)));
		populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource(data)));
		initializer.setDatabasePopulator(populator);
		return initializer;
	}
}