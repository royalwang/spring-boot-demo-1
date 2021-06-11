package com.bambrow.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private HelloBean helloBean;

    public HelloBean getHelloBean() {
        return helloBean;
    }

    @Autowired
    public void setHelloBean(HelloBean helloBean) {
        this.helloBean = helloBean;
    }

    @RequestMapping("/hello")
    public String hello() {
        return String.format("Hello %s from Spring Boot!", helloBean.getName());
    }
}
