package ca.est.security;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import ca.est.cache.EndpointCache;
import ca.est.entity.Endpoint;
import ca.est.entity.GatewayResponse;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

/**
 * @author Estevam Meneses SecurityManager will validate the request according
 *         with requirements
 */
@Log4j2
public abstract class SecurityManager {

	protected GatewayResponse validRequest(ServerWebExchange exchange) {
		String path = exchange.getRequest().getURI().getPath().toString();
		String method = exchange.getRequest().getMethod().toString();
		// log.info("Request: {} {}", path, method);
		
		if(path.contains("h2")) {
			log.info("H2 WEB UI disabled.");
			return new GatewayResponse(false, exchange, null);
		}

		Optional<Endpoint> endpoint = EndpointCache.getInstance().findEndpoint(method, path);
		if (endpoint.isPresent()) {
			return new GatewayResponse(true, exchange, null);
		}
		return new GatewayResponse(false, exchange, "");

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