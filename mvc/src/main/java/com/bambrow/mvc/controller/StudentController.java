package com.bambrow.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bambrow.mvc.pojo.Student;
import com.bambrow.mvc.pojo.StudentList;

import java.util.Arrays;

@Controller
@RequestMapping("/student")
public class StudentController {

    // /student/get?id=[]&name=[]
    // user-defined class
    // can also detect user-defined object in class
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String getStudent(Model model, Student student) {
        model.addAttribute("message", student.toString());
        return "demo";
    }

    // /student/array?id=[]&id=[]&id=[]...
    // array
    // can also use List<T> field in user-defined class
    @RequestMapping(value = "array", method = RequestMethod.GET)
    public String getIDs(Model model, Integer[] id) {
        model.addAttribute("message", Arrays.toString(id));
        return "demo";
    }

    // /student/students?students%5B0%5D.id=[]&students%5B0%5D.name=[]...
    // array
    // %5B stands for '['
    // %5D stands for ']'
    // %20 stands for ' '
    // example: /student/students?students%5B0%5D.id=10001&students%5B0%5D.name=david&students%5B1%5D.id=10002&students%5B1%5D.name=zach
    @RequestMapping(value = "students", method = RequestMethod.GET)
    public String getStudents(Model model, StudentList students) {
        model.addAttribute("message", students.toString());
        return "demo";
    }

}
