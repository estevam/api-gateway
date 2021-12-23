package com.est.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.est.security.GatewayResponse;
import com.est.security.SecurityManager;

import reactor.core.publisher.Mono;

/**
 * 
 * @author Estevam Meneses
 */
@Component
public class SecurityFilter extends SecurityManager implements GatewayFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		GatewayResponse gatewayRequest = this.validRequest(exchange);
		if (gatewayRequest.isValideRequest()) {
			return chain.filter(exchange.mutate().request(exchange.getRequest()).build());
		}
		return this.requestError(gatewayRequest.getExchange(),gatewayRequest.getError());
	}
}
