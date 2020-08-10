Steps:

1. start spring application
2. confirm actuator working at : http://localhost:8080/actuator/prometheus
3. prometheus and grafana should be up
4. modify prometheus.yml to include below job:
      - job_name: 'spring-boot-actuator-demo'
        scrape_interval: 5s
        metrics_path: /actuator/prometheus
        static_configs:
        - targets: ['localhost:8080']
5. restart prometheus.
6. grafana id: 6756