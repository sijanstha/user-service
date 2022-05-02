package com.sijan.userservice.service;

import com.sijan.userservice.entity.Role;
import com.sijan.userservice.entity.User;
import com.sijan.userservice.mapper.UserMapper;
import com.sijan.userservice.model.UserDomain;
import com.sijan.userservice.repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private UserServiceCache.RoleCache roleCache;
    private UserMapper mapper;
    private PasswordEncoder passwordEncoder;

    public Optional<UserDomain> create(UserDomain request) {
        request.validate();
        User entity = mapper.toEntity(request);
        List<Role> roleList = request.getRoleIds()
                .stream()
                .map(id -> {
                    Optional<Role> optionalRole = roleCache.lookUpRole(id);
                    if (optionalRole.isEmpty()) {
                        throw new IllegalStateException(String.format("Role with id %d not found", id));
                    }
                    return optionalRole.get();
                })
                .collect(Collectors.toList());
        entity.setRoles(roleList);

        try {
            User user = userRepository.saveAndFlush(entity);

            // TODO: broadcast this user info to internal kafka topic
            // so that other services will know
            return Optional.ofNullable(mapper.toDomain(user));
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException)
                throw new IllegalStateException(String.format("Email %s already taken", request.getEmail()));

            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    public Boolean checkUserForAuthentication(String email, String password) {
        if (!StringUtils.hasText(email) || !StringUtils.hasText(password))
            return false;

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty())
            return false;

        String hashedPassword = passwordEncoder.encode(password);
        if (!optionalUser.get().getPassword().equals(hashedPassword))
            return false;

        return true;
    }

    public Optional<UserDomain> getByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty())
            return Optional.empty();

        return Optional.ofNullable(mapper.toDomain(optionalUser.get()));
    }

    public Optional<UserDomain> getById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id );
        if (optionalUser.isEmpty())
            return Optional.empty();

        return Optional.ofNullable(mapper.toDomain(optionalUser.get()));
    }
}
