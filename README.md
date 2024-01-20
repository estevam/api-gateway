# API gateway using Spring Cloud Gateway 

##### API gateway is an API management tool that sits between a client and a collection of backend services.
##### Why use an API gateway? 
Most enterprise APIs are deployed via API gateways. It’s common for API gateways to handle common tasks that are used across a system of API services, such as user authentication, rate limiting, and statistics.

- Authentication and authorization. A gateway is your first line of defense against potential attackers that can perform basic security functions: antivirus scanning, token translation, decryption and encryption, validation, and many more.

- Log tracing and aggregation. A gateway keeps detailed audit logs used for debugging, reporting, and analytics.

- Rate limiting. A gateway enforces policies against resource overuse (either accidental or deliberate) and allows you to configure API invocation at runtime, so the service is consumed only at the required rate.

- You may have adopted a microservices architecture, in which case a single request could require calls to dozens of distinct applications.

- Over time you’ll add some new API services and retire others, but your clients will still want to find all your services in the same place.

#### Dependency version 
###### Java 21
###### Spring Boot 3.2.1	
###### WebFlux 6.1.2
###### Spring Cloud 2023.0.0	
###### Spring Gateway 4.1.0
###### H2 Database: 2.2.224
		
##### The router after starting will create on H2 the table 'endpoint' and insert follow row.

```
CREATE TABLE endpoint (
  id BIGINT auto_increment  PRIMARY KEY,
  uri_request VARCHAR(500) NOT NULL,
  url_destination VARCHAR(500) NOT NULL,
  enable BOOLEAN DEFAULT FALSE,
  http_method VARCHAR(4) DEFAULT NULL
);
  
INSERT INTO endpoint VALUES (1, '/blog/api/user', 'http://localhost:8080/blog/api/user', TRUE, 'GET' ); 
```


#### Testing:
##### Send request
```sh
http://localhost:8888/blog/api/user [GET] 

```

##### The request will route to the microservice running on port 8080.
#### Ex: uri_request "http://localhost:8888/blog/api/user" will be route to url_destination= "http://localhost:8080/blog/api/user"

```sh
     http://localhost:8080/blog/api/user [GET]

```