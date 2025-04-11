package com.udh.auth.application.service;

import com.udh.auth.domain.entity.AuthUser;
import com.udh.auth.domain.entity.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    public Token login(String username, String password) {

    }
}
