package com.bambrow.hdfs.controller;

import com.bambrow.hdfs.service.HdfsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Api(tags = {"HDFS"})
@RestController
public class HdfsController {

    @Autowired
    private HdfsService hdfsService;

    @ApiOperation(value = "list files", produces = MediaType.TEXT_HTML_VALUE, notes = "returns all files under that path")
    @GetMapping(value = { "/ls",  "/ls/{path}/**" })
    public String ls(@ApiParam(value = "path", required = false) @PathVariable(value = "path", required = false) String path, HttpServletRequest request) {
        if (path == null) {
            path = "";
        } else {
            String arguments = arguments(request);
            if (arguments != null && !arguments.isEmpty()) {
                path = path +  "/" + arguments;
            }
        }
        List<Map<String, Object>> list = hdfsService.ls(path, null);
        StringBuilder builder = new StringBuilder();
        initializeBuilder(builder);
        list.stream()
                .sorted(Comparator.comparing(x -> x.get("path").toString()))
                .forEach(x -> builder.append(builderItem(x)));
        builder.append("</p></body></html>");
        return builder.toString();
    }

    private String builderItem(Map<String, Object> item) {
        if ((boolean) item.get("isDir")) {
            return String.format("<a href=\"/ls%s\">%s</a> <br />", item.get("path"), item.get("path"));
        } else {
            return String.format("<a href=\"/open%s\">%s</a> <br />", item.get("path"), item.get("path"));
        }
    }

    @ApiOperation(value = "open file", produces = MediaType.TEXT_HTML_VALUE, notes = "returns file content in text")
    @GetMapping(value = { "/open/{path}/**" })
    public String open(@ApiParam(value = "path", required = false) @PathVariable(value = "path", required = false) String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String arguments = arguments(request);
        if (arguments != null && !arguments.isEmpty()) {
            path = path +  "/" + arguments;
        }
        StringBuilder builder = new StringBuilder();
        if (hdfsService.isDirectory(path)) {
            response.sendRedirect("/ls/" + path);
        } else {
            initializeBuilder(builder);
            builder.append(hdfsService.openString(path));
            builder.append("</p></body></html>");
        }
        return builder.toString();
    }

    private String arguments(HttpServletRequest request) {
        String afterPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        return new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, afterPath);
    }

    private void initializeBuilder(StringBuilder builder) {
        builder.append("<!DOCTYPE html>").append("<html xmlns:th=\"http://www.thymeleaf.org\">");
        builder.append("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /></head>");
        builder.append("<body><p>");
    }

}
