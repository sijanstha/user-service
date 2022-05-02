package com.sijan.userservice.mapper;

import com.sijan.userservice.entity.Role;
import com.sijan.userservice.model.RoleDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RoleMapper extends Mapper<Role, RoleDomain> {
    public RoleMapper() {
        super(new Role(), new RoleDomain());
    }

    @Override
    public Role toEntity(RoleDomain domain) {
        var entity = super.toEntity(domain);
        entity.setRole(domain.getRole());
        log.info("role [{}]", entity);
        return entity;
    }

    @Override
    public RoleDomain toDomain(Role entity) {
        var domain = super.toDomain(entity);
        domain.setId(entity.getId());
        domain.setRole(entity.getRole());
        return domain;
    }
}
