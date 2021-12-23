package com.est.security;

import org.springframework.web.server.ServerWebExchange;

/**
 * 
 * @author Estevam Meneses
 *
 */
public class GatewayResponse {
	
	private boolean isValideRequest;
	private ServerWebExchange exchange;
	private String error;
	
	public GatewayResponse(boolean isValideRequest, ServerWebExchange exchange, String error) {
		super();
		this.isValideRequest = isValideRequest;
		this.exchange = exchange;
		this.error = error;
	}

	public boolean isValideRequest() {
		return isValideRequest;
	}

	public void setValideRequest(boolean isValideRequest) {
		this.isValideRequest = isValideRequest;
	}

	public ServerWebExchange getExchange() {
		return exchange;
	}

	public void setExchange(ServerWebExchange exchange) {
		this.exchange = exchange;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
