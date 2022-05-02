package com.sijan.userservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

@Getter
@Setter
@ToString
public class RoleDomain extends AbstractDomain{
    private String role;

    @Override
    public void validate() {
        if (!StringUtils.hasText(role))
            throw new IllegalStateException("Role is required");
    }
}
