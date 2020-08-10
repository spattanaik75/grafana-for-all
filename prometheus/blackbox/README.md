WE CAN MONITOR WEBSITES UPTIME USING THIS

download blackbox_exporter for amd64 and put it in current directory

STEPS to RUN:
> docker build -t blackbox .
> docker run -d -p 9115:9115 --name blackbox blackbox

ADD CONFIG in prometheus.yml

```
  - job_name: blackbox
    metrics_path: /probe
    params:
        module: [http_2xx]
    static_configs:
        - targets:
        - https://www.robustperception.io/
        - http://prometheus.io/blog
        - http://yourdomain/usage-api/health
        - http://yourdomain/google-apm/health
        - https://google.com            
        - https://www.telegraph.co.uk
        
    relabel_configs:
        - source_labels: [__address__]
        target_label: __param_target
        - source_labels: [__param_target]
        target_label: instance
        - target_label: __address__
        replacement: localhost:9115 # Blackbox exporter.
```

USAGE:
Test with : http://localhost:9115/probe?target=google.com&module=http_2xx
Should return: probe_success 1