package com.est.entity;

import org.springframework.data.annotation.Id;

/**
 * 
 * @author Estevam Meneses
 * Dec. 29, 2021
 */
public class Endpoint {
    @Id
    private Integer id;
    private String uri_request;
    private String url_destination;
    private boolean enable;
    private String http_method;
	public Endpoint() {
		// TODO Auto-generated constructor stub
	}
	public Endpoint(Integer id, String uri_request, String url_destination, boolean enable, String http_method) {
		super();
		this.id = id;
		this.uri_request = uri_request;
		this.url_destination = url_destination;
		this.enable = enable;
		this.http_method = http_method;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the uri_request
	 */
	public String getUri_request() {
		return uri_request;
	}
	/**
	 * @param uri_request the uri_request to set
	 */
	public void setUri_request(String uri_request) {
		this.uri_request = uri_request;
	}
	/**
	 * @return the url_destination
	 */
	public String getUrl_destination() {
		return url_destination;
	}
	/**
	 * @param url_destination the url_destination to set
	 */
	public void setUrl_destination(String url_destination) {
		this.url_destination = url_destination;
	}
	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}
	/**
	 * @param enable the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	/**
	 * @return the http_method
	 */
	public String getHttp_method() {
		return http_method;
	}
	/**
	 * @param http_method the http_method to set
	 */
	public void setHttp_method(String http_method) {
		this.http_method = http_method;
	}
	@Override
	public String toString() {
		return "Endpoint [id=" + id + ", uri_request=" + uri_request + ", url_destination=" + url_destination
				+ ", enable=" + enable + ", http_method=" + http_method + "]";
	}
}
