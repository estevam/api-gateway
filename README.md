# API Router using Spring Cloud Gateway 

#### Spring Cloud Gateway provides a simple, yet effective way to route to APIs and provide cross cutting concerns to them such as security, monitoring, metrics, and resiliency.

##### Application router start will create on H2 the table 'endpoint' and insert follow row.

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