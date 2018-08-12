package com.est.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GatewayFilter {

    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("==> Method:{} ",exchange.getRequest().getMethod().toString());
        log.info("==> Host:{} ", exchange.getRequest().getURI().getHost().toString());
        log.info("==> Path:{}",  exchange.getRequest().getURI().getPath().toString());
        log.info("==> QueryParams:{}", exchange.getRequest().getQueryParams().toString());
        return chain.filter(exchange);
	}
}
