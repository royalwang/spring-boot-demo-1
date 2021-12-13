package com.bambrow.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Log4j2Application {

    private static final Logger log = LoggerFactory.getLogger(Log4j2Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Log4j2Application.class, args);
    }

    @RequestMapping("/")
    public String testLog() {
        log.debug("debug log");
        log.info("info log");
        log.warn("warn log");
        log.error("error log");
        return "Log completed. Please check the log folder.";
    }

}
