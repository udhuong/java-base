package com.udh.auth.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUser {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
}
