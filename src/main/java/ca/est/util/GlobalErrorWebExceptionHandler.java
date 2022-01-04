package ca.est.util;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
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
 *         All the request catch by the exception handler will return the HTTP
 *         status bad request.
 * 
 *         https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/autoconfigure/web/reactive/error/AbstractErrorWebExceptionHandler.html
 */
@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

	/**
	 * GlobalErrorWebExceptionHandler
	 * 
	 * @param globalErrorAttributes
	 * @param applicationContext
	 * @param serverCodecConfigurer
	 */
	public GlobalErrorWebExceptionHandler(GlobalErrorAttributes globalErrorAttributes,
			ApplicationContext applicationContext, ServerCodecConfigurer serverCodecConfigurer) {
		super(globalErrorAttributes, new WebProperties.Resources(), applicationContext);
		super.setMessageWriters(serverCodecConfigurer.getWriters());
		super.setMessageReaders(serverCodecConfigurer.getReaders());
	}

	/**
	 * RoutingFunction
	 * 
	 * @param errorAttributes
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
		final Map<String, Object> errorPropertiesMap  = getErrorAttributes(request, ErrorAttributeOptions.defaults());
		//final Map<String, Object> errorPropertiesMap = new HashMap<>(); 
		return ServerResponse.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(errorPropertiesMap));
	}

}