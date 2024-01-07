# API gateway using Spring Cloud Gateway 

##### API gateway is an API management tool that sits between a client and a collection of backend services.
##### Why use an API gateway? 
- Most enterprise APIs are deployed via API gateways. It’s common for API gateways to handle common tasks that are used across a system of API services, such as user authentication, rate limiting, and statistics.

- At its most basic, an API service accepts a remote request and returns a response. But real life is never that simple. Consider your various concerns when you host large-scale APIs.
You want to protect your APIs from overuse and abuse, so you use an authentication service and rate limiting. 

- You want to understand how people use your APIs, so you’ve added analytics and monitoring tools.
If you have monetized APIs, you’ll want to connect to a billing system.

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

##### It will route to other microservice running on port 8080.

```sh
 http://http://localhost:8080/blog/api/user [GET]

```