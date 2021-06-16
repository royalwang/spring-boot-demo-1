package com.bambrow.mysql.repository;

import com.bambrow.mysql.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(String name);

    List<User> findByUsername(String username);

    List<User> findByIdLessThan(Long id);

    List<User> findByIdLessThanEqual(Long id);

    List<User> findByIdGreaterThan(Long id);

    List<User> findByIdGreaterThanEqual(Long id);

    List<User> findByNameLike(String name);

    List<User> findByNameStartingWith(String name);

    List<User> findAllByOrderByNameAsc();

    @Query(value = "SELECT COUNT(*) FROM user WHERE username IS NOT NULL", nativeQuery = true)
    int countValidUsers();

    @Transactional
    void deleteByName(String name);

    @Transactional
    void deleteByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user u SET username = LOWER(name) WHERE username IS NULL", nativeQuery = true)
    void fillUsername();

}
