package com.bambrow.kubernetesjava.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KubernetesJavaConfig {

    @Value("${k8s.masterUrl}")
    private String masterUrl;

    public String getMasterUrl() {
        return masterUrl;
    }

    public void setMasterUrl(String masterUrl) {
        this.masterUrl = masterUrl;
    }

}
