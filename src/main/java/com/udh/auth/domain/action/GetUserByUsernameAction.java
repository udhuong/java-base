package com.udh.auth.domain.action;

import com.udh.auth.domain.entity.AuthUser;
import com.udh.auth.domain.exception.AppException;
import com.udh.java_base.domain.contract.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserByUsernameAction {
    private final UserRepository userRepository;

    /**
     * Lấy thông tin người dùng theo username
     *
     * @param username tên đăng nhập
     * @return thông tin người dùng
     */
    public AuthUser handle(@NotNull String username) {
        AuthUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new AppException("Không tìm thấy người dùng");
        }
        return user;
    }
}
