package com.udh.java_base.infrastructure.repository;

import com.udh.auth.domain.entity.AuthUser;
import com.udh.java_base.domain.contract.UserRepository;
import com.udh.java_base.domain.entity.User;
import com.udh.java_base.infrastructure.database.java_base.entity.UserEntity;
import com.udh.java_base.infrastructure.database.java_base.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User getById(Long id) {
        UserEntity userEntity = userJpaRepository.findById(id).orElse(null);

        User user = new User();
        if (userEntity != null) {
            user.setId(userEntity.getId());
            user.setName(userEntity.getName());
            user.setEmail(userEntity.getEmail());
        }
        return user;
    }

    @Override
    public AuthUser findByUsername(String username) {
        UserEntity entity = userJpaRepository.findByUsername(username);
        if (entity == null) {
            return null;
        }
        AuthUser authUser = new AuthUser();
        authUser.setId(entity.getId());
        authUser.setUsername(entity.getUsername());
        authUser.setPassword(entity.getPassword());
        authUser.setEmail(entity.getEmail());
        authUser.setName(entity.getName());
        return authUser;
    }
}
