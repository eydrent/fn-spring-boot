package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.service.UserSercice;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {
    private UserSercice userSercice;

    public DeleteUser(UserSercice userSercice) {
        this.userSercice = userSercice;
    }

    public void remove(Long id) {
        userSercice.delete(id);
    }
}
