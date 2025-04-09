package com.udh.java_base.domain.action;

import com.udh.java_base.domain.contract.UserRepository;
import com.udh.java_base.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserDetailByIdAction {
    private final UserRepository userRepository;

    public User handle(Long id) {
        return userRepository.getById(id);
    }
}
