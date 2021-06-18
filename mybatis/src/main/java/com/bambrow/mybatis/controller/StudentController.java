package com.bambrow.mybatis.controller;

import cn.hutool.core.lang.Dict;
import com.bambrow.mybatis.entity.Student;
import com.bambrow.mybatis.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/get/{id}")
    public Dict select(@PathVariable Long id) {
        Student s = studentService.select(id);
        return Dict.create().set("code", s != null ? "200" : "500").set("data", s);
    }

    @RequestMapping("/getName/{name}")
    public Dict select(@PathVariable String name) {
        List<Student> ss = studentService.select(name);
        return Dict.create().set("code", "200").set("data", ss);
    }

    @RequestMapping("/get")
    public Dict selectAll() {
        List<Student> ss = studentService.selectAll();
        return Dict.create().set("code", "200").set("data", ss);
    }

    @RequestMapping("/insert")
    public Dict insert(@RequestParam("name") String name,
                       @RequestParam("username") String username,
                       @RequestParam(value = "age", required = false) Integer age,
                       @RequestParam(value = "email", required = false) String email)
    {
        boolean insert = false;
        try {
            if (age != null && email != null) {
                insert = studentService.insert(name, username, age, email);
            } else if (email != null) {
                insert = studentService.insert(name, username, email);
            } else if (age != null) {
                insert = studentService.insert(name, username, age);
            } else {
                insert = studentService.insert(name, username);
            }
        }
        catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return Dict.create().set("code", insert ? "200" : "500").set("message", insert ? "Success" : "Fail");
    }

    @RequestMapping("/update")
    public Dict update(@RequestParam("id") Long id,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "username", required = false) String username,
                       @RequestParam(value = "age", required = false) Integer age,
                       @RequestParam(value = "email", required = false) String email)
    {
        boolean update = false, transaction = true;
        try {
            if (name != null) {
                boolean b = studentService.updateName(id, name);
                update |= b;
                transaction &= b;
            }
            if (username != null) {
                boolean b = studentService.updateUsername(id, username);
                update |= b;
                transaction &= b;
            }
            if (age != null) {
                boolean b = studentService.updateAge(id, age);
                update |= b;
                transaction &= b;
            }
            if (email != null) {
                boolean b = studentService.updateEmail(id, email);
                update |= b;
                transaction &= b;
            }
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return Dict.create()
                .set("code", (transaction && update) ? "200" : "500")
                .set("message", (transaction && update) ? "Success" :
                        (transaction ? "Nothing to Update" :
                                (update ? "Partially Updated" : "Fail")));
    }

    @RequestMapping("/delete/{id}")
    public Dict delete(@PathVariable Long id) {
        boolean delete = studentService.delete(id);
        return Dict.create().set("code", delete ? "200" : "500").set("message", delete ? "Success" : "Fail");
    }

    @RequestMapping("/deleteAll")
    public Dict deleteAllUsers() {
        boolean delete = studentService.deleteAll();
        return Dict.create().set("code", delete ? "200" : "500").set("message", delete ? "Success" : "Fail");
    }

    @RequestMapping("/reset")
    public Dict reset() {
        boolean reset = studentService.reset();
        return Dict.create().set("code", reset ? "200" : "500").set("message", reset ? "Success" : "Fail");
    }

    @RequestMapping("/fillEmail")
    public Dict fillEmail() {
        boolean fill = studentService.fillEmail();
        return Dict.create().set("code", fill ? "200" : "500").set("message", fill ? "Success" : "Fail");
    }

    @RequestMapping("/countAdults")
    public Dict countAdults() {
        int count = studentService.countAdults();
        return Dict.create().set("code", "200").set("count", count);
    }

}
