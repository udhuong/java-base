package com.udh.java_base.infrastructure.repository;

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
}
