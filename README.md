# API Router using Spring Cloud Gateway 

#### Spring Cloud Gateway provides a simple, yet effective way to route to APIs and provide cross cutting concerns to them such as security, monitoring/metrics, and resiliency.

#### Testing:

###### Send a any http request[POST, DELETE, GET , PUT, PATCH] to http://localhost:8888 
######  Header 
###### key: X-CF-Forwarded-Url
###### value: http://localhost:8080/test/hi (local rest API)
