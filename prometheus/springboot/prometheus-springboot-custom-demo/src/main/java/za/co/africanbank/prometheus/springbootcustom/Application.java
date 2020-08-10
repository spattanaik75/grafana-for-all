package za.co.africanbank.prometheus.springbootcustom;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

// Standard Spring boot annotation
@SpringBootApplication
// Add a Prometheus metrics enpoint to the route `/prometheus`. `/metrics` is already taken by Actuator.
@EnablePrometheusEndpoint
// Pull all metrics from Actuator and expose them as Prometheus metrics. Must have permission to do this.
//@EnableSpringBootMetricsCollector
// For route annotations below.
@RestController
// Main application class. Keep it in one file for simplicity.
public class Application {
    // A Histogram Prometheus Metric
    static final Counter counter = Counter.build().namespace("java").name("custom_counter").help("This is my counter").register();
    static final Gauge gauge = Gauge.build().namespace("java").name("custom_gauge").help("This is my gauge").register();
    static final Histogram histogram = Histogram.build().namespace("java").name("custom_histogram").help("This is my histogram").register();
    static final Summary summary = Summary.build().namespace("java").name("custom_summary").help("This is my summary").register();
    // Register must be called to add it to the output

    private static double rand(double min, double max) {
        return min + (Math.random() * (max - min));
    }

    // Standard MVC style route mapping
    @RequestMapping("/")
    // Note that we could have used the Spring AOP annotation @PrometheusTimeMethod too.
    String root() {
        // Start the histogram timer
        counter.inc(rand(0, 5));
        gauge.set(rand(-5, 10));
        histogram.observe(rand(0, 5));
        summary.observe(rand(0, 5));

        return "Visit ";

    }

    // Standard Spring boot main.
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    counter.inc(rand(0, 5));
                    gauge.set(rand(-5, 10));
                    histogram.observe(rand(0, 5));
                    summary.observe(rand(0, 5));


                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
