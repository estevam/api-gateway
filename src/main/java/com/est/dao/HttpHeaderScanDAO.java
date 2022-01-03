package com.est.dao;

import org.springframework.web.server.ServerWebExchange;

/**
 * 
 * @author Estevam Meneses
 * Jan. 3, 2022
 */
public interface HttpHeaderScanDAO {
	public boolean isJndiLookup(ServerWebExchange exchange);
}
