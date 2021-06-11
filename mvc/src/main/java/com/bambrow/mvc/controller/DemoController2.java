package com.bambrow.mvc.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An example of controller by implementing Controller interface.
 * This is the old-fashioned way. Not recommended now.
 */

public class DemoController2 implements Controller {
    // /demo
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return new ModelAndView("demo", "message", "I'm a controller implementing Controller interface!");
    }
}
