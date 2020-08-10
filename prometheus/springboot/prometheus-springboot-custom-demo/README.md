# Prometheus Java Spring Boot Custom

This is a quick example of how to instrument your Java Spring Boot app
with the Prometheus client.

This project is built with:

- Java 8
- Spring Boot 1.5.8
- Prometheus Java SimpleClient 0.1.0

See the [POM file](./pom.xml) for more details.

## Usage
- To check your histogram sample is working. Hit http://localhost:8080/ a couple of times in your browser
- And observe http://localhost:8080/prometheus. Check http_request_duration_seconds metric

## Prometheus

To instrument our Java code we need to manipulate the metrics each
time a new HTTP request is received.

See [the application](src/main/java/za/co/africanbank/prometheus/springbootcustom/Application.java) for more details.

As an added bonus, Prometheus is able to scrape the Spring Boot
Actuactor metrics for free!

## Running
Run using spring boot and simply open port 8080 


