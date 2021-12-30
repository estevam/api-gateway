# API Router using Spring Cloud Gateway 

#### Spring Cloud Gateway provides a simple, yet effective way to route to APIs and provide cross cutting concerns to them such as security, monitoring/metrics, and resiliency.

#### Testing:

##### Application router start will create on H2 the table 'endpoint' and insert one row

```
CREATE TABLE endpoint (
  id INT auto_increment  PRIMARY KEY,
  uri_request VARCHAR(500) NOT NULL,
  url_destination VARCHAR(500) NOT NULL,
  enable BOOLEAN DEFAULT FALSE,
  http_method VARCHAR(4) DEFAULT NULL
);
  
INSERT INTO endpoint VALUES (1, '/microservice_memory_used', 'http://localhost:7777', TRUE, 'GET' ); 
```


#### Testing:
##### Send request
```sh
http://localhost:8888/microservice_memory_used [GET] 

```

##### It will route to other microservice running on port 7777.

```sh
 http://localhost:7777 [GET]

```