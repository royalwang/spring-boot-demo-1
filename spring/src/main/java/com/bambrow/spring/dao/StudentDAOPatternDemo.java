package com.bambrow.spring.dao;

public class StudentDAOPatternDemo {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAOImpl();
        dao.insertStudent(101, "Amy");
        dao.insertStudent(102, "Bob");
        dao.insertStudent(103, "Cathy");

        for (Student s : dao.getAllStudents()) {
            System.out.println(s);
        }
        // Student{name='Amy', id=101}
        // Student{name='Bob', id=102}
        // Student{name='Cathy', id=103}

        System.out.println(dao.getStudent(101));
        System.out.println(dao.getStudent(104));
        // Student{name='Amy', id=101}
        // This id does not exist!
        // null

        System.out.println(dao.insertStudent(101, "David"));
        // This id already exists!
        // Student{name='Amy', id=101}

        System.out.println(dao.containsStudent(101));
        System.out.println(dao.containsStudent(104));
        // true
        // false

        System.out.println(dao.updateStudent(101, "Alice"));
        System.out.println(dao.updateStudent(104, "David"));
        // Student{name='Alice', id=101}
        // This id does not exist!
        // null

        System.out.println(dao.deleteStudent(101));
        System.out.println(dao.deleteStudent(104));
        // Student{name='Alice', id=101}
        // This id does not exist!
        // null
    }
}
