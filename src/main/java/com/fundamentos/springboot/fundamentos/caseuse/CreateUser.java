package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.service.UserSercice;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserSercice userSercice;

    public CreateUser(UserSercice userSercice) {
        this.userSercice = userSercice;
    }

    public User save(User newUser) {
        return userSercice.save(newUser);
    }
}
