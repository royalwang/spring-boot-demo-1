package com.bambrow.yarn.service;

import com.bambrow.yarn.config.YarnConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.api.records.QueueInfo;
import org.apache.hadoop.yarn.api.records.YarnApplicationState;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class YarnService {

    @Autowired
    private YarnConfig yarnConfig;

    private YarnClient client;

    @PostConstruct
    private void init() {
        Configuration configuration = new YarnConfiguration();
        configuration.set("yarn.resourcemanager.address", yarnConfig.getAddress());
        configuration.set("yarn.resourcemanager.admin.address", yarnConfig.getAdminAddress());
        client = YarnClient.createYarnClient();
        client.init(configuration);
        client.start();
    }

    public List<ApplicationReport> getApplications() {
        try {
            return client.getApplications();
        } catch (IOException | YarnException e) {
            log.error("YarnService getApplications: " + e.getMessage());
            return null;
        }
    }

    public List<ApplicationReport> getApplications(String... states) {
        try {
            return client.getApplications(parseApplicationStates(states));
        } catch (IOException | YarnException e) {
            log.error("YarnService getApplications: " + e.getMessage());
            return null;
        }
    }

    private EnumSet<YarnApplicationState> parseApplicationStates(String... states) {
        List<String> stateList = Arrays.stream(states).map(String::trim).map(String::toUpperCase).collect(Collectors.toList());
        log.info("YarnService parseApplicationStates: " + String.join(",", stateList));
        Set<YarnApplicationState> stateSet = stateList.stream().map(YarnApplicationState::valueOf).collect(Collectors.toSet());
        return EnumSet.copyOf(stateSet);
    }

    public List<QueueInfo> getQueues() {
        try {
            return client.getRootQueueInfos();
        } catch (IOException | YarnException e) {
            log.error("YarnService getQueues: " + e.getMessage());
            return null;
        }
    }

    @PreDestroy
    private void clean() {
        client.stop();
    }

}
