package com.bambrow.redis.mapper;

import com.bambrow.redis.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM student")
    List<Student> selectAll();

    @Select("SELECT * FROM student WHERE id = #{id}")
    Student selectById(@Param("id") Long id);

    // @Provider demo
    // not used in this module
    @SelectProvider(type = StudentProvider.class, method = "selectByNameLike")
    List<Student> selectByNameLike(String name);

    @Select("SELECT * FROM student WHERE name = #{name}")
    List<Student> selectByName(@Param("name") String name);

    @Select("SELECT * FROM student WHERE username = #{username}")
    List<Student> selectByUsername(@Param("username") String username);

    @Insert("INSERT INTO student(name, username, age, email) VALUES (#{name}, #{username}, #{age}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(Student student);

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
    // not used in this module because of cache; reset implemented differently
    void resetTable();

}
