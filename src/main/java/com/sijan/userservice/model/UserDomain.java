package com.sijan.userservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDomain extends AbstractDomain {
    private String name;
    private String email;
    private String password;
    private String address;
    private List<String> roles;
    private List<Integer> roleIds;

    @Override
    public void validate() {

        // TODO: More complex validation logic
        if (!StringUtils.hasText(name)) {
            throw new IllegalStateException("Name is required");
        }

        if (!StringUtils.hasText(email)) {
            throw new IllegalStateException("Email is required");
        }

        if (!StringUtils.hasText(password)) {
            throw new IllegalStateException("Password is required");
        }

        if (roleIds == null || roleIds.isEmpty()) {
            throw new IllegalStateException("At least one role is required");
        }
    }
}
