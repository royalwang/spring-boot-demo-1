package com.bambrow.yarn.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class YarnConfig {

    @Value("${yarn.resourcemanager.address}")
    private String address;

    @Value("${yarn.resourcemanager.admin-address}")
    private String adminAddress;

}
