package com.est.cache;
import java.util.List;
import java.util.Optional;

import com.est.entity.Endpoint;

/**
 * 
 * @author Estevam Meneses
 * Dec. 30, 2021
 */
public class EndpointCache {

	private static EndpointCache instance = null;
    private List<Endpoint> endpointList;
    
	private EndpointCache() {
		// TODO Auto-generated constructor stub
	}

	public static EndpointCache getInstance() {
		if (instance == null) {
			instance = new EndpointCache();
		}
		return instance;
	}

	public void setEndpointList(List<Endpoint> endpointList) {
		if(endpointList.size() > 2) {
			endpointList.clear();
		}
		this.endpointList = endpointList;
	}
	
	public List<Endpoint> getEndpointList() {
		return this.endpointList;
	}
	
	public Optional<Endpoint> findEndpoint(String httpMethod, String uriRequest) {
		return endpointList.stream().filter(e ->  e.getHttp_method().equals(httpMethod) && e.getUri_request().equals(uriRequest)).findFirst();
	}	
}
