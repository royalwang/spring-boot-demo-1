package com.bambrow.mysql;

import com.bambrow.mysql.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class StudentJdbcTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void getStudentsTest() {
        String sql = "select * from student";
        List<Student> studentList = jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = Student.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .username(resultSet.getString("username"))
                        .age(resultSet.getInt("age"))
                        .email(resultSet.getString("email"))
                        .build();
                return student;
            }
        });
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

}
