package com.udh.java_base.domain.contract;

import com.udh.auth.domain.entity.AuthUser;
import com.udh.java_base.domain.entity.User;

public interface UserRepository {
   User getById(Long id);
   AuthUser findByUsername(String username);
}
