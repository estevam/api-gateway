package com.est.filter;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.est.RouteConfig;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * 
 * @author Estevam Meneses
 *
 */
@Component
public class RouteForwardingFilter implements GatewayFilter {
	
	private static final Logger log = LoggerFactory.getLogger(RouteForwardingFilter.class);


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("ServerWebExchange: {}", exchange.toString());
        log.info("GatewayFilterChain: {}", chain.toString());

        try {
        	
            String forwardUrl = exchange.getRequest().getHeaders().get(RouteConfig.FORWARDED_URL).get(0);
            exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, new URI(forwardUrl));
            //return chain.filter(exchange);
              
       return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                ServerHttpResponse response = exchange.getResponse();
                //Manipulate the response in some way
            
                log.info("response {}", response.getHeaders().toString());
   
            }));
            
        } catch (URISyntaxException e) {
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return Mono.empty();
        }
    } 
    
    
    public Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }
}

