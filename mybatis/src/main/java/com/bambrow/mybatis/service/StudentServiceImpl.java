package com.bambrow.mybatis.service;

import com.bambrow.mybatis.entity.Student;
import com.bambrow.mybatis.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }

    @Override
    public Student select(Long id) {
        return studentMapper.selectById(id);
    }

    @Override
    public List<Student> select(String name) {
        return studentMapper.selectByNameLike(name);
    }

    @Override
    public boolean insert(String name, String username) {
        return studentMapper.insert(name, username, null, null);
    }

    @Override
    public boolean insert(String name, String username, Integer age) {
        return studentMapper.insert(name, username, age, null);
    }

    @Override
    public boolean insert(String name, String username, String email) {
        return studentMapper.insert(name, username, null, email);
    }

    @Override
    public boolean insert(String name, String username, Integer age, String email) {
        return studentMapper.insert(name, username, age, email);
    }

    @Override
    public boolean updateName(Long id, String name) {
        Student student = studentMapper.selectById(id);
        if (student == null || name == null || name.length() == 0) {
            return false;
        }
        return studentMapper.updateNameById(id, name);
    }

    @Override
    public boolean updateUsername(Long id, String username) {
        Student student = studentMapper.selectById(id);
        if (student == null || username == null || username.length() == 0) {
            return false;
        }
        return studentMapper.updateUsernameById(id, username);
    }

    @Override
    public boolean updateAge(Long id, Integer age) {
        Student student = studentMapper.selectById(id);
        if (student == null || age == null) {
            return false;
        }
        return studentMapper.updateAgeById(id, age);
    }

    @Override
    public boolean updateEmail(Long id, String email) {
        Student student = studentMapper.selectById(id);
        if (student == null || email == null || email.length() == 0) {
            return false;
        }
        return studentMapper.updateEmailById(id, email);
    }

    @Override
    public boolean delete(Long id) {
        return studentMapper.deleteById(id);
    }

    @Transactional
    @Override
    public boolean deleteAll() {
        return studentMapper.deleteAll();
    }

    @Transactional
    @Override
    public boolean reset() {
        studentMapper.resetTable();
        return true;
    }

    @Transactional
    @Override
    public boolean fillEmail() {
        return studentMapper.fillEmail();
    }

    @Override
    public int countAdults() {
        return studentMapper.countAdults();
    }
}
