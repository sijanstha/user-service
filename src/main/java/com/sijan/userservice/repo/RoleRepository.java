package com.sijan.userservice.repo;

import com.sijan.userservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "select r from Role r where r.role = ?1 and r.active = true and r.deleted = false")
    Optional<Role> findByRole(String role);

    @Query(value = "select r from Role r where r.id = ?1 and r.active = true and r.deleted = false")
    Optional<Role> findById(Integer id);

    @Query(value = "select r from Role r where r.active = true and r.deleted = false")
    List<Role> findAll();
}
