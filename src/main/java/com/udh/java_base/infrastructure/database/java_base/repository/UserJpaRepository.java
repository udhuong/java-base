package com.udh.java_base.infrastructure.database.java_base.repository;

import com.udh.java_base.infrastructure.database.java_base.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
