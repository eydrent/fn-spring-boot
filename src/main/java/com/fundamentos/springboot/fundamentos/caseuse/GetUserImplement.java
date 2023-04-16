package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.service.UserSercice;

import java.util.List;

public class GetUserImplement implements GetUser {

    private UserSercice userSercice;

    public GetUserImplement(UserSercice userSercice) {
        this.userSercice = userSercice;
    }

    @Override
    public List<User> getAll() {
        return userSercice.getAllUsers();
    }
}
