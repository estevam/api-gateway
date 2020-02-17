package com.est.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import com.est.RouteConfig;

import reactor.core.publisher.Mono;

/**
 * @author estevam.meneses
 */
public abstract class SecurityManager {

	private static final Logger log = LoggerFactory.getLogger(SecurityManager.class);

	protected GatewayResponse validRequest(ServerWebExchange exchange) {
		String url = exchange.getRequest().getHeaders().get(RouteConfig.FORWARDED_URL).get(0);
		log.info("URL :{}", url);
		return new GatewayResponse(false, exchange, null);
	}

	/**
	 * Request error
	 * 
	 * @param exchange
	 * @return
	 */
	protected Mono<Void> requestError(ServerWebExchange exchange, String error) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(HttpStatus.UNAUTHORIZED);
		if (error != null) {
			response.getHeaders().add("Error:", error);
		}
		return response.setComplete();
	}

}
