package com.est.util;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * @author estevam.meneses
 * 
 * All the request catch by the exception handler will return the HTTP
 * status bad request.
 * 
 * https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/autoconfigure/web/reactive/error/AbstractErrorWebExceptionHandler.html
 */
@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

	/**
	 * Global error web exception handler
	 * 
	 * @param errorAttributes
	 * @param resourceProperties
	 * @param applicationContext
	 * @param configurer
	 */
	GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
			ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
		super(errorAttributes, resourceProperties, applicationContext);
		this.setMessageWriters(configurer.getWriters());
	}

	/**
	 * Routing errors
	 */
	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}

	/**
	 * Render error response
	 * 
	 * @param request
	 * @return
	 */
	private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
		return ServerResponse.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.empty());
	}
}