package com.est.service;

import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import com.est.dao.HttpHeaderScanDAO;

/**
 * 
 * @author Estevam Meneses Jan. 3, 2022
 * 
 */
public class HttpHeaderScanService implements HttpHeaderScanDAO {
	private static final Logger log = LoggerFactory.getLogger(HttpHeaderScanService.class);
	private static final String JNDI = "jndi";

	public HttpHeaderScanService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Check if request header have JNDI lookup URI format: ${jndi:ldap://...}
	 * 
	 * @param exchange
	 * @return boolean
	 */
	public boolean isJndiLookup(ServerWebExchange exchange) {
		HttpHeaders headers = exchange.getRequest().getHeaders();
		for (Entry<String, List<String>> header : headers.entrySet()) {
			log.info("{} :{}",header.getKey(), header.getValue());
			if (header.getValue().toString().contains(JNDI)){
				log.warn("JNDI lookup detected by IP: {}", exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
				return true;
			}
		}
		return false;
	}
}
