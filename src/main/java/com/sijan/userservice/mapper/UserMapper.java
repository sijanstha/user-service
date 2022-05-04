package com.sijan.userservice.mapper;

import com.sijan.userservice.entity.User;
import com.sijan.userservice.model.UserDomain;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper extends Mapper<User, UserDomain> {

    public UserMapper() {
        super(new User(), new UserDomain());
    }

    @Override
    public User toEntity(UserDomain domain) {
        var entity = super.toEntity(domain);
        entity.setName(domain.getName());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setAddress(domain.getAddress());
        return entity;
    }

    @Override
    public UserDomain toDomain(User entity) {
        var domain = super.toDomain(entity);
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setEmail(entity.getEmail());
        domain.setAddress(entity.getAddress());
        domain.setRoles(entity.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList()));
        return domain;
    }
}
