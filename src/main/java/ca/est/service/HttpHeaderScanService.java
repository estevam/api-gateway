package ca.est.service;

import org.springframework.web.server.ServerWebExchange;

import ca.est.dao.HttpHeaderScanDAO;

/**
 * 
 * @author Estevam Meneses Jan. 3, 2022
 * 
 */
public class HttpHeaderScanService implements HttpHeaderScanDAO {
	//private static final Logger log = LoggerFactory.getLogger(HttpHeaderScanService.class);


	public HttpHeaderScanService() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean existHeader(ServerWebExchange exchange) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
     *
	 * Header scan
	 * @param exchange
	 * @return boolean
	 *
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
	*/
}
