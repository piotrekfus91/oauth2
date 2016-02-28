package pl.iaeste.caseweek.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "pl.iaeste.caseweek")
public class DropboxApp {
    public static void main(String[] args) {
        SpringApplication.run(DropboxApp.class, args);
    }
}
