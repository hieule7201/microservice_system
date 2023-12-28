package com.minhhieu.userservice.service.Impl;

import com.minhhieu.userservice.model.User;
import com.minhhieu.userservice.repository.UserRepository;
import com.minhhieu.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public User getUserById(long idUser) {
        return userRepository.findById(idUser).orElseThrow(()-> new UsernameNotFoundException("Not found"));
    }
}
