package com.bambrow.yarn.controller;

import com.bambrow.yarn.service.YarnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.api.records.QueueInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@Api(tags = {"YARN"})
@RestController
public class YarnController {

    @Autowired
    private YarnService yarnService;

    @ApiOperation(value = "get applications", produces = MediaType.TEXT_HTML_VALUE, notes = "returns all applications")
    @GetMapping(value = { "/app", "/application" })
    public String getApplications() {
        StringBuilder builder = new StringBuilder();
        List<ApplicationReport> applicationList = yarnService.getApplications();
        if (applicationList == null) {
            return "Error getting application list";
        }
        applicationList.stream()
                .sorted(Comparator.comparing(ApplicationReport::getApplicationId))
                .forEach(x -> builder.append(builderAppInfo(x)));
        return finalizeBuilder(builder).toString();
    }

    @ApiOperation(value = "get applications by states", produces = MediaType.TEXT_HTML_VALUE, notes = "returns all applications by states")
    @GetMapping(value = { "/app/{states}", "/application/{states}" })
    public String getApplications(@ApiParam(value = "states", required = true, example = "FINISHED") @PathVariable String states) {
        String[] stateArr = states.split(",");
        StringBuilder builder = new StringBuilder();
        List<ApplicationReport> applicationList = yarnService.getApplications(stateArr);
        if (applicationList == null) {
            return "Error getting application list";
        }
        applicationList.stream()
                .sorted(Comparator.comparing(ApplicationReport::getApplicationId))
                .forEach(x -> builder.append(builderAppInfo(x)));
        return finalizeBuilder(builder).toString();
    }

    private String builderAppInfo(ApplicationReport appInfo) {
        return "Application ID: " + appInfo.getApplicationId() + " <br />"
                + "&nbsp;&nbsp; Application Name: " + appInfo.getName() + " <br />"
                + "&nbsp;&nbsp; Application Queue: " + appInfo.getQueue() + " <br />"
                + "&nbsp;&nbsp; Application User: " + appInfo.getUser() + " <br />"
                + "&nbsp;&nbsp; Application State: " + appInfo.getYarnApplicationState().toString() + " <br />";
    }

    @ApiOperation(value = "get queues", produces = MediaType.TEXT_HTML_VALUE, notes = "returns all queues")
    @GetMapping(value = "/queue")
    public String getQueues() {
        StringBuilder builder = new StringBuilder();
        List<QueueInfo> queueList = yarnService.getQueues();
        if (queueList == null || queueList.isEmpty()) {
            return "Error getting queue list";
        }
        queueList.stream()
                .sorted(Comparator.comparing(QueueInfo::getQueueName))
                .forEach(x -> builder.append(builderQueueInfo(x, 0)));
        return finalizeBuilder(builder).toString();
    }

    private String builderQueueInfo(QueueInfo queueInfo, int depth) {
        List<QueueInfo> childQueueList = queueInfo.getChildQueues();
        if (childQueueList.isEmpty()) {
            return prefixDepth(depth) + queueInfo.getQueueName() + " <br />";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append(prefixDepth(depth)).append(queueInfo.getQueueName()).append(" <br />");
            childQueueList.stream()
                    .sorted(Comparator.comparing(QueueInfo::getQueueName))
                    .forEach(x -> builder.append(builderQueueInfo(x, depth + 1)));
            return builder.toString();
        }
    }

    private String prefixDepth(int depth) {
        if (depth <= 0) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                builder.append("&nbsp;&nbsp;");
            }
            return builder.toString();
        }
    }

    private StringBuilder finalizeBuilder(StringBuilder builder) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>").append("<html xmlns:th=\"http://www.thymeleaf.org\">");
        html.append("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /></head>");
        html.append("<body><p>");
        html.append(builder);
        html.append("</p></body></html>");
        return html;
    }

}
