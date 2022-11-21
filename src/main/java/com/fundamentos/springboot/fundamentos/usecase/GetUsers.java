package com.fundamentos.springboot.fundamentos.usecase;


import com.fundamentos.springboot.fundamentos.entity.User;

import java.util.List;

public interface GetUsers {
    List<User> getAll();
}
