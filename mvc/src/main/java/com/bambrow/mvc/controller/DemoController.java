package com.bambrow.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An example of controller.
 */

@Controller
@RequestMapping("")
public class DemoController {

    // @RequestMapping
    // name: mapping name (usually not used)
    // value: path
    // path: same as value, or 404 Not Found
    // method: HTTP request, or 405 Method Not Allowed, example: method = RequestMethod.GET
    // consumes: consumable media types, [content-type] in headers, or 415 Unsupported Media Type, example: consumes = "text/html"
    // produces: producible media types, [Accept] in headers, or 406 Not Acceptable, example: produces="application/json; charset=UTF-8"
    // headers: headers, or 404 Not Found, example: headers = "Host=localhost:8088"
    // params: parameters, or 400 Bad Request, example: params = {"username=David","password!=123456"}

    // /
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String jump() {
        // redirecting
        return "redirect: ./index";
    }

    // /index
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String hello(ModelMap model) {
        // add an attribute to index.jsp
        model.addAttribute("message", "Hello Spring MVC!");
        // this will be translated to index.jsp
        return "index";
    }

    // /path/[]
    @RequestMapping(value = "/path/{p1}")
    // get path variable from URI
    public String getPath(@PathVariable String p1, ModelMap model) {
        model.addAttribute("message", "in path: " + p1);
        // use demo.jsp
        return "demo";
    }

    // /name/[regex_match][regex_match]
    // example: /name/david10001
    @RequestMapping(value = "/name/{name:[a-zA-Z]{1,10}}{id:\\d+}")
    // regular expression matching
    public String getPath2(@PathVariable String name, @PathVariable int id, ModelMap model) {
        model.addAttribute("message", "name: " + name + "; id: " + id);
        return "demo";
    }

    /*
    @RequestMapping(value = "/param", method = RequestMethod.GET)
    public ModelAndView getParam(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        return null;
    }
     */

    // /param?username=[]&password=[]
    @RequestMapping(value = "/param", method = RequestMethod.GET)
    // get params from URI
    public ModelAndView getParam(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println(username);
        System.out.println(password);
        return null;
    }

    // /ant/[].do
    @RequestMapping(value = "/ant/*.do")
    // ? any single char
    // * zero or more chars
    // ** zero or more dirs
    public String getAntPattern(ModelMap model) {
        model.addAttribute("message", "Welcome to ANT style!");
        return "demo";
    }

}
