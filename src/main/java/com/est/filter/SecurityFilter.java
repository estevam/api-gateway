package com.est.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.est.cache.EndpointCache;
import com.est.entity.Endpoint;
import com.est.repository.EndpointRepository;
import com.est.security.GatewayResponse;
import com.est.security.SecurityManager;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author Estevam Meneses
 */
@Component
public class SecurityFilter extends SecurityManager implements GatewayFilter {
	
	@Autowired
	private EndpointRepository endpointRepository;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		
        Flux<Endpoint> endpointFLux  = endpointRepository.findAll();
        
        List<Endpoint>  listEndpoint = new ArrayList<>();
        endpointFLux.collectList().subscribe(listEndpoint::addAll);

        EndpointCache.getInstance().setEndpointList(listEndpoint);
		
		GatewayResponse gatewayRequest = this.validRequest(exchange);
		if (gatewayRequest.isValideRequest()) {
			return chain.filter(exchange.mutate().request(exchange.getRequest()).build());
		}
		return this.requestError(gatewayRequest.getExchange(),gatewayRequest.getError());
	}
}
