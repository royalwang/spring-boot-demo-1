package com.bambrow.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    private static final long serialVersionUID = -1L;

    private Long id;
    private String name;
    private String username;
    private Integer age;
    private String email;

}
