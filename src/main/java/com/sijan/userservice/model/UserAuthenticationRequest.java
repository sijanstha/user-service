package com.sijan.userservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserAuthenticationRequest {
    private String email;
    private String password;
}
