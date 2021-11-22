package com.bambrow.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAsync
@SpringBootApplication
@RestController
public class AsyncApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello from async demo! Please run the unit tests.";
    }

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }

}
