package com.bambrow.mybatis.mapper;

import com.bambrow.mybatis.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM student")
    List<Student> selectAll();

    @Select("SELECT * FROM student WHERE id = #{id}")
    Student selectById(@Param("id") Long id);

    // @Provider demo
    @SelectProvider(type = StudentProvider.class, method = "selectByNameLike")
    List<Student> selectByNameLike(String name);

    @Insert("INSERT INTO student(name, username, age, email) VALUES (#{name}, #{username}, #{age}, #{email})")
    boolean insert(@Param("name") String name, @Param("username") String username, @Param("age") Integer age, @Param("email") String email);

    @Update("UPDATE student SET name = '${name}' WHERE id = ${id}")
    boolean updateNameById(@Param("id") Long id, @Param("name") String name);

    @Update("UPDATE student SET username = '${username}' WHERE id = ${id}")
    boolean updateUsernameById(@Param("id") Long id, @Param("username") String username);

    @Update("UPDATE student SET age = ${age} WHERE id = ${id}")
    boolean updateAgeById(@Param("id") Long id, @Param("age") Integer age);

    @Update("UPDATE student SET email = '${email}' WHERE id = ${id}")
    boolean updateEmailById(@Param("id") Long id, @Param("email") String email);

    @Delete("DELETE FROM student WHERE id = #{id}")
    boolean deleteById(Long id);

    @Delete("DELETE FROM student")
    boolean deleteAll();

    // xml demo
    // defined in resources/mappers/StudentMapper.xml
    // for one method, cannot have both annotation and xml
    void resetTable();

    @Update("UPDATE student SET email = CONCAT(LOWER(username), '@a.com') WHERE email IS NULL")
    boolean fillEmail();

    @Select("SELECT COUNT(*) FROM student WHERE age > 18")
    int countAdults();

}
