DROP TABLE IF EXISTS endpoint;

CREATE TABLE endpoint (
  id BIGINT auto_increment  PRIMARY KEY,
  uri_request VARCHAR(500) NOT NULL,
  url_destination VARCHAR(500) NOT NULL,
  enable BOOLEAN DEFAULT FALSE,
  http_method VARCHAR(4) DEFAULT NULL
);
  