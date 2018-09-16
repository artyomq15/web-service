package by.bsu.mmf.webservice.touragency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "by.bsu.mmf.webservice")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
