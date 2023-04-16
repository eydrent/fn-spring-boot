package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.service.UserSercice;
import org.springframework.stereotype.Component;

@Component
public class UpdateUser {
    private UserSercice userSercice;

    public UpdateUser(UserSercice userSercice) {
        this.userSercice = userSercice;
    }

    public User update(User newUser, Long id) {
        return userSercice.update(newUser, id);
    }
}
