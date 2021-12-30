/**
 * 
 */
package com.est.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.est.entity.Endpoint;

import reactor.core.publisher.Flux;

/**
 * @author Estevam Meneses
 * Dec. 29, 2021
 */
public interface EndpointRepository extends ReactiveCrudRepository<Endpoint, Long> {

    @Query("SELECT * FROM endpoint WHERE url_destination = :urlDestination")
    Flux<Endpoint> findByUrlDestination(String urlDestination);

    @Query("SELECT * FROM endpoint WHERE uri_request = :uriRequest")
    Flux<Endpoint> findByUriRequest(String uriRequest);
}
