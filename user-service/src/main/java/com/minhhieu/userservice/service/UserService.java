package com.minhhieu.userservice.service;

import com.minhhieu.userservice.model.User;

import java.util.Optional;

public interface UserService {
    User getUserById(long idUser);
}
