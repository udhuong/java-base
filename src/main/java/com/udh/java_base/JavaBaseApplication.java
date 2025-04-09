package com.udh.java_base;

import com.udh.java_base.domain.action.GetUserDetailByIdAction;
import com.udh.java_base.domain.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching
@EnableAsync
@SpringBootApplication
public class JavaBaseApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JavaBaseApplication.class, args);

        GetUserDetailByIdAction action = context.getBean(GetUserDetailByIdAction.class);
        User user = action.handle(1L); // giả sử id = 1
    }
}
