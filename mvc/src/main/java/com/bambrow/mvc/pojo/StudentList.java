package com.bambrow.mvc.pojo;

import com.bambrow.mvc.pojo.Student;

import java.util.List;

public class StudentList {
    private List<Student> students;

    public StudentList() {}

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Student student : students) {
            str.append(student.toString());
            str.append("<br/>");
        }
        return str.toString();
    }
}
