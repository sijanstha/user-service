package com.sijan.userservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "tbl_role")
public class Role extends AbstractEntity {

    @Column(name = "role", nullable = false, unique = true)
    private String role;

    public Role(String role) {
        this.role = role;
    }

    public Role(Integer id) {
        this.setId(id);
    }
}
