package com.bambrow.spring.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Book com.bambrow.spring.pojo. @Component but @Value from .properties file.
 */

@Component
public class Book {
    @Value("${uuid}")
    private long uuid;
    @Value("${name}")
    private String name;
    @Value("${author}")
    private String author;
    @Value("${year}")
    private int year;

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
