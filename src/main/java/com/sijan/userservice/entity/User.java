package com.sijan.userservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_users")
public class User extends AbstractEntity {

    @Column(name = "full_name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, updatable = false, unique = true)
    private String email;

    @Column(name = "hash_password", nullable = false)
    private String password;

    @Column(name = "address")
    private String address;

    @ManyToMany
    private List<Role> roles;
}
