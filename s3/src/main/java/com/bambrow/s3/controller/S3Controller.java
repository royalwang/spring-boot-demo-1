package com.bambrow.s3.controller;

import com.bambrow.s3.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @GetMapping("/ls")
    public String listBuckets(Model model) {
        model.addAttribute("isTopLevel", true);
        model.addAttribute("buckets", s3Service.listBuckets());
        return "index";
    }

    @GetMapping("/ls/{bucket}/**")
    public String listObjects(@PathVariable(value = "bucket", required = true) String bucket,
                              HttpServletRequest request,
                              Model model)
    {
        log.info("S3Controller listObjects: bucket = " + bucket);
        String delimiter = "/";
        String key = arguments(request);
        if (key == null) {
            key = "";
        }
        if (!key.isEmpty() && !key.endsWith(delimiter)) {
            key += delimiter;
        }
        log.info("S3Controller listObjects: key = " + key);
        String prefix = bucket + delimiter + key;
        log.info("S3Controller listObjects: prefix = " + prefix);
        model.addAttribute("isTopLevel", false);
        model.addAttribute("prefix", prefix);
        model.addAttribute("results", s3Service.listPrefixesAndObjects(bucket, key));
        return "index";
    }

    @GetMapping("/open/{bucket}/**")
    public String open(@PathVariable(value = "bucket", required = true) String bucket,
                       HttpServletRequest request,
                       Model model)
    {
        log.info("S3Controller open: bucket = " + bucket);
        String delimiter = "/";
        String key = arguments(request);
        if (key == null) {
            key = "";
        }
        log.info("S3Controller open: key = " + key);
        String path = bucket + delimiter + key;
        log.info("S3Controller open: path = " + path);
        model.addAttribute("path", path);
        model.addAttribute("content", s3Service.openString(bucket, key));
        return "file";
    }

    private String arguments(HttpServletRequest request) {
        String afterPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        return new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, afterPath);
    }

}
