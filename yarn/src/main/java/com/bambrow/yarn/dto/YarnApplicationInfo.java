package com.bambrow.yarn.dto;

import org.apache.hadoop.yarn.api.records.ApplicationReport;

public class YarnApplicationInfo {
    private String appId;
    private String appName;
    private String appQueue;
    private String appUser;
    private String appState;

    public YarnApplicationInfo() {}

    public YarnApplicationInfo(String appId, String appName, String appQueue, String appUser, String appState) {
        this.appId = appId;
        this.appName = appName;
        this.appQueue = appQueue;
        this.appUser = appUser;
        this.appState = appState;
    }

    public YarnApplicationInfo(ApplicationReport appReport) {
        this.appId = appReport.getApplicationId().toString();
        this.appName = appReport.getName();
        this.appQueue = appReport.getQueue();
        this.appUser = appReport.getUser();
        this.appState = appReport.getYarnApplicationState().toString();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppQueue() {
        return appQueue;
    }

    public void setAppQueue(String appQueue) {
        this.appQueue = appQueue;
    }

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public String getAppState() {
        return appState;
    }

    public void setAppState(String appState) {
        this.appState = appState;
    }
}
