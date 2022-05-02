package com.sijan.userservice.service;

import com.sijan.userservice.entity.Role;
import com.sijan.userservice.mapper.RoleMapper;
import com.sijan.userservice.model.RoleDomain;
import com.sijan.userservice.repo.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;
    private RoleMapper mapper;

    public Optional<RoleDomain> create(RoleDomain request) {
        request.validate();
        try {
            Role createdRole = roleRepository.saveAndFlush(mapper.toEntity(request));
            return Optional.of(mapper.toDomain(createdRole));
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                throw new IllegalStateException("Role with name " + request.getRole() + " already exists");
            }
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    public List<RoleDomain> findAll() {
        var roles = roleRepository.findAll();
        if (roles.isEmpty())
            return Collections.emptyList();
        return roles.stream().map(role -> mapper.toDomain(role)).collect(Collectors.toList());
    }
}
