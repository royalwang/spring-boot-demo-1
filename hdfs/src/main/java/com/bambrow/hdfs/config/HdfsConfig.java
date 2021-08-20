package com.bambrow.hdfs.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class HdfsConfig {

    @Value("${hdfs.root}")
    private String root;

    @Value("${hdfs.user}")
    private String user;

}
