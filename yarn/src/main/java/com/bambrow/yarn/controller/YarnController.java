package com.bambrow.yarn.controller;

import com.bambrow.yarn.service.YarnService;
import com.bambrow.yarn.utils.YarnControllerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.api.records.QueueInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Api(tags = {"YARN"})
@Controller
public class YarnController {

    @Autowired
    private YarnService yarnService;

    @ApiOperation(value = "get applications", produces = MediaType.TEXT_HTML_VALUE, notes = "returns all applications")
    @GetMapping(value = { "/app", "/application" })
    public String getApplications(Model model) {
        List<ApplicationReport> appReports = yarnService.getApplications();
        model.addAttribute("appList", YarnControllerUtils.getYarnApplicationInfoList(appReports));
        return "app";
    }

    @ApiOperation(value = "get applications by states", produces = MediaType.TEXT_HTML_VALUE, notes = "returns all applications by states")
    @GetMapping(value = { "/app/{states}", "/application/{states}" })
    public String getApplications(@ApiParam(value = "states", required = true, example = "FINISHED") @PathVariable String states, Model model) {
        String[] stateArr = states.split(",");
        List<ApplicationReport> appReports = yarnService.getApplications(stateArr);
        model.addAttribute("appList", YarnControllerUtils.getYarnApplicationInfoList(appReports));
        return "app";
    }

    @ApiOperation(value = "get queues", produces = MediaType.TEXT_HTML_VALUE, notes = "returns all queues")
    @GetMapping(value = "/queue")
    public String getQueues(Model model) {
        List<QueueInfo> queueInfos = yarnService.getQueues();
        if (queueInfos == null) {
            queueInfos = new ArrayList<>();
        }
        queueInfos.sort(Comparator.comparing(QueueInfo::getQueueName));
        model.addAttribute("queueList", queueInfos);
        return "queue";
    }

}
