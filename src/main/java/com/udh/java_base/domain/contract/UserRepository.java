package com.udh.java_base.domain.contract;

import com.udh.java_base.domain.entity.User;

public interface UserRepository {
   User getById(Long id);
}
