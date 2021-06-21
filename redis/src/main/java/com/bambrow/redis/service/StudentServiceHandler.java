package com.bambrow.redis.service;

import com.bambrow.redis.entity.Student;
import com.bambrow.redis.mapper.StudentMapper;
import com.bambrow.redis.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/*
This service is used since AOP does not work within
the method calls from the same class.

https://stackoverflow.com/questions/13564627/spring-aop-not-working-for-method-call-inside-another-method

The aspect is applied to a proxy surrounding the bean.
Note that everytime you get a reference to a bean,
it's not actually the class referenced in your config,
but a synthetic class implementing the relevant interfaces,
delegating to the actual class and adding functionality,
such as your AOP.

When you're calling directly on the class, whereas if that
class instance is injected into another as a Spring bean,
it's injected as its proxy, and hence method calls will
be invoked on the proxy (and the aspects will be triggered)

If you want to achieve this, you could split method1/method2
into separate beans, or use a non-spring-orientated AOP framework.
 */
@Service
@Slf4j
public class StudentServiceHandler {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    @CachePut(value = Constants.STUDENT_ID, key = "#student.id")
    public Student insert(Student student) {
        boolean insert = studentMapper.insert(student);
        if (insert) {
            log.info("Inserting MySQL: id = " + student.getId());
        } else {
            log.info("Inserting MySQL: insertion failed");
        }
        return student;
    }

    @CachePut(value = Constants.STUDENT_ID, key = "#id")
    public Student update(Long id, String name, String username, Integer age, String email) {
        Student student = studentService.select(id);
        if (name != null) {
            student.setName(name);
            studentMapper.updateNameById(id, name);
        } else if (username != null) {
            student.setUsername(username);
            studentMapper.updateUsernameById(id, username);
        } else if (age != null) {
            student.setAge(age);
            studentMapper.updateAgeById(id, age);
        } else if (email != null) {
            student.setEmail(email);
            studentMapper.updateEmailById(id, email);
        }
        return student;
    }

    @CacheEvict(value = Constants.STUDENT_ID, key = "#student.id")
    public boolean delete(Student student) {
        log.info("Deleting MySQL: id = " + student.getId());
        return studentMapper.deleteById(student.getId());
    }

    @CacheEvict(value = Constants.STUDENT_ID, allEntries = true)
    @Transactional
    public boolean deleteAll() {
        log.info("Deleting MySQL: delete all");
        return studentMapper.deleteAll();
    }

}
