package com.udh.auth.presentation.http.controller;

import com.udh.auth.application.service.AuthService;
import com.udh.auth.domain.entity.Token;
import com.udh.auth.presentation.http.request.LoginRequest;
import com.udh.common.presentation.http.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * Đăng nhập lấy token
     */
    public ResponseEntity<Response> login(@Valid @RequestBody LoginRequest request) {
        Token token = authService.login(request.username(), request.password());
        return Response.success("Đăng nhập thành công");
    }
}
