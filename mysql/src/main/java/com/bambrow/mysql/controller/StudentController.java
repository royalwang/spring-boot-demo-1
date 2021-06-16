package com.bambrow.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/get")
    public List<Map<String, Object>> getStudents() {
        String sql = "select * from student";
        List<Map<String, Object>> listMap = jdbcTemplate.queryForList(sql);
        return listMap;
    }

    @RequestMapping("/get/{id}")
    public Map<String, Object> getStudent(@PathVariable Long id) {
        String sql = "select * from student where id = " + id;
        Map<String, Object> map = jdbcTemplate.queryForMap(sql);
        return map;
    }

}
