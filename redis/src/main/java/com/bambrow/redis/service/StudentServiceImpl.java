package com.bambrow.redis.service;

import com.bambrow.redis.entity.Student;
import com.bambrow.redis.mapper.StudentMapper;
import com.bambrow.redis.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentServiceHandler studentServiceHandler;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init() {
        log.info("StudentService: initialization");
        List<Student> studentList = selectAll();
        Set<String> keys = new HashSet<>(Objects.requireNonNull(redisTemplate.keys(Constants.S_ID_REDIS + "*")));
        redisTemplate.delete(keys);
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        for (Student student : studentList) {
            valueOperations.set(Constants.S_ID_REDIS + student.getId(), student);
        }
    }

    @Override
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }

    @Cacheable(value = Constants.STUDENT_ID, key = "#id")
    @Override
    public Student select(Long id) {
        log.info("Querying MySQL: id = " + id);
        return studentMapper.selectById(id);
    }

    @Override
    public List<Student> selectByName(String name) {
        log.info("Querying MySQL: name = " + name);
        return studentMapper.selectByName(name);
    }

    @Override
    public List<Student> selectByUsername(String username) {
        log.info("Querying MySQL: username = " + username);
        return studentMapper.selectByUsername(username);
    }

    @Override
    public boolean insert(String name, String username) {
        return insert(name, username, null, null);
    }

    @Override
    public boolean insert(String name, String username, Integer age) {
        return insert(name, username, age, null);
    }

    @Override
    public boolean insert(String name, String username, String email) {
        return insert(name, username, null, email);
    }

    @Override
    public boolean insert(String name, String username, Integer age, String email) {
        Student student = Student.builder().name(name).username(username).age(age).email(email).build();
        studentServiceHandler.insert(student);
        return student.getId() != null && student.getId() > 0;
    }

    private boolean exists(Long id) {
        if (id == null) {
            return false;
        }
        return redisTemplate.hasKey(Constants.S_ID_REDIS + id);
    }

    @Override
    public boolean updateName(Long id, String name) {
        boolean exists = exists(id);
        if (!exists || name == null || name.length() == 0) {
            return false;
        }
        studentServiceHandler.update(id, name, null, null, null);
        return true;
    }

    @Override
    public boolean updateUsername(Long id, String username) {
        boolean exists = exists(id);
        if (!exists || username == null || username.length() == 0) {
            return false;
        }
        studentServiceHandler.update(id, null, username, null, null);
        return true;
    }

    @Override
    public boolean updateAge(Long id, Integer age) {
        boolean exists = exists(id);
        if (!exists || age == null) {
            return false;
        }
        studentServiceHandler.update(id, null, null, age, null);
        return true;
    }

    @Override
    public boolean updateEmail(Long id, String email) {
        boolean exists = exists(id);
        if (!exists || email == null || email.length() == 0) {
            return false;
        }
        studentServiceHandler.update(id, null, null, null, email);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Student student = studentMapper.selectById(id);
        if (student == null) {
            log.info("Deleting MySQL: id = " + id + " does not exist");
            return false;
        }
        return studentServiceHandler.delete(student);
    }

    @Override
    public boolean deleteAll() {
        return studentServiceHandler.deleteAll();
    }

    @Transactional
    @Override
    public boolean reset() {
        log.info("StudentService: reset");
        deleteAll();
        insert("Amy", "amy", 18, "amy@a.com");
        insert("Bob", "bob", 19, "bob@a.com");
        insert("Cathy", "cat", 22);
        insert("David", "david");
        insert("Eva", "eva", "eva@a.com");
        return true;
    }

    @PreDestroy
    public void cleanup() {
        log.info("StudentService: clean up");
        deleteAll();
    }

}
