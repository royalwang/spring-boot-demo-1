package com.bambrow.hdfs.controller;

import com.bambrow.hdfs.dto.FileObject;
import com.bambrow.hdfs.service.HdfsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Api(tags = {"HDFS"})
@Controller
public class HdfsController {

    @Autowired
    private HdfsService hdfsService;

    @ApiOperation(value = "list files", produces = MediaType.TEXT_HTML_VALUE, notes = "returns all files under that path")
    @GetMapping(value = { "/ls",  "/ls/{path}/**" })
    public String ls(@ApiParam(value = "path", required = false) @PathVariable(value = "path", required = false) String path,
                     HttpServletRequest request,
                     Model model)
    {
        if (path == null) {
            path = "";
        } else {
            String arguments = arguments(request);
            if (arguments != null && !arguments.isEmpty()) {
                path = path +  "/" + arguments;
            }
        }
        List<FileObject> list = hdfsService.ls(path, null);
        list.sort(Comparator.comparing(FileObject::getPath));
        model.addAttribute("path", path);
        model.addAttribute("fileList", list);
        return "index";
    }

    @ApiOperation(value = "open file", produces = MediaType.TEXT_HTML_VALUE, notes = "returns file content in text")
    @GetMapping(value = { "/open", "/open/{path}/**" })
    public String open(@ApiParam(value = "path", required = false) @PathVariable(value = "path", required = false) String path,
                       HttpServletRequest request,
                       HttpServletResponse response,
                       Model model) throws IOException
    {
        if (path == null) {
            path = "";
        } else {
            String arguments = arguments(request);
            if (arguments != null && !arguments.isEmpty()) {
                path = path +  "/" + arguments;
            }
        }
        if (hdfsService.isDirectory(path)) {
            response.sendRedirect("/ls/" + path);
        } else {
            model.addAttribute("path", path);
            model.addAttribute("content", hdfsService.openString(path));
        }
        return "file";
    }

    private String arguments(HttpServletRequest request) {
        String afterPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        return new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, afterPath);
    }

}
