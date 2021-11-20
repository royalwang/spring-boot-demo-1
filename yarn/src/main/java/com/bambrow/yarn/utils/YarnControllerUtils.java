package com.bambrow.yarn.utils;

import com.bambrow.yarn.dto.YarnApplicationInfo;
import org.apache.hadoop.yarn.api.records.ApplicationReport;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class YarnControllerUtils {

    public static List<YarnApplicationInfo> getYarnApplicationInfoList(List<ApplicationReport> appReports) {
        if (appReports == null) {
            appReports = new ArrayList<>();
        }
        return appReports.stream()
                .sorted(Comparator.comparing(ApplicationReport::getApplicationId))
                .map(YarnApplicationInfo::new).collect(Collectors.toList());
    }

}
