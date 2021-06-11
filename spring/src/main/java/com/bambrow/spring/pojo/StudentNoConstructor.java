package com.bambrow.spring.pojo;

/**
 * Same as Student com.bambrow.spring.pojo but with no constructor.
 * For testing p-namespace.
 *   see student_context.xml
 */

public class StudentNoConstructor {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudentNoConstructor{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
