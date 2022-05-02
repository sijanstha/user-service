package com.sijan.userservice.service;

import com.sijan.userservice.entity.Role;
import com.sijan.userservice.repo.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class UserServiceCache {

    @Autowired
    private RoleRepository roleRepository;

    @Bean("roles")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public RoleCache roles() {
        return new RoleCache();
    }

    public class RoleCache {
        private Map<String, Role> roleCacheForId = new HashMap<>();
        private Map<Integer, Role> roleCacheForName = new HashMap<>();

        public RoleCache() {
            roleRepository.findAll().stream().forEach(role -> {
                roleCacheForId.put(role.getRole().toLowerCase(), role);
                roleCacheForName.put(role.getId(), role);
            });
            log.info("Roles loaded");
            log.info(roleCacheForId.toString());
        }

        public Optional<Role> lookupRole(String name) {
            var role = roleCacheForId.getOrDefault(name.toLowerCase(), null);
            if (role == null) {
                log.info("Role not found in cache");

                Optional<Role> optionalRole = roleRepository.findByRole(name);
                if (optionalRole.isPresent()) {
                    roleCacheForId.put(name.toLowerCase(), optionalRole.get());
                }
                return optionalRole;
            }

            return Optional.ofNullable(role);
        }

        public Optional<Role> lookUpRole(Integer id) {
            var role = roleCacheForName.getOrDefault(id, null);
            if (role == null) {
                log.info("Role not found in cache");

                Optional<Role> optionalRole = roleRepository.findById(id);
                if (optionalRole.isPresent()) {
                    roleCacheForName.put(id, optionalRole.get());
                }
                return optionalRole;
            }
            return Optional.ofNullable(role);
        }
    }

}
