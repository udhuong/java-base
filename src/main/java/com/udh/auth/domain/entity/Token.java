package com.udh.auth.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Token {
    private String tokenType;
    private Long expiresIn;
    private String accessToken;
    private String refreshToken;
    private List<String> $scopes;
}
