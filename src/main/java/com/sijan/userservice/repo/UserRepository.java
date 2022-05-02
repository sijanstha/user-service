package com.sijan.userservice.repo;

import com.sijan.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select u from User u where u.email = ?1 and u.active = true and u.deleted = false")
    Optional<User> findByEmail(String email);

    @Query(value = "select u from User u where u.id = ?1 and u.active = true and u.deleted = false")
    Optional<User> findById(Integer id);
}

