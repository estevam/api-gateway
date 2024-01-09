package ca.est.config;

import static org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter.ROUTE_TO_URL_FILTER_ORDER;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.est.filter.LoggingFilter;
import ca.est.filter.RouteForwardingFilter;
import ca.est.filter.SecurityFilter;



/**
 * @author Estevam Meneses
 */
@Configuration
public class RouteConfig {

	//public static final String FORWARDED_URL = "X-CF-Forwarded-Url";
	//public static final String PROXY_SIGNATURE = "X-CF-Proxy-Signature";
	
	private static final Logger log = LoggerFactory.getLogger(RouteConfig.class);

	@Bean
	RouteLocator customRouteLocator(RouteLocatorBuilder builder,SecurityFilter securityFilter, LoggingFilter loggingFilter,
			RouteForwardingFilter forwardingFilter) {

		log.info("Loading routes...", builder.toString());

		return builder.routes().route(r -> 
		r.alwaysTrue().filters(f -> {
			f.filter(securityFilter); // security
			f.filter(loggingFilter);  // log all requests
			f.filter(forwardingFilter, ROUTE_TO_URL_FILTER_ORDER + 1);

			return f;
			
		}).uri("http://localhost:8888")).build();
	}
}
