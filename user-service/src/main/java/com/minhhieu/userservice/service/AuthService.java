package com.minhhieu.userservice.service;

import com.minhhieu.userservice.payload.request.LoginRequest;
import com.minhhieu.userservice.payload.request.SignupRequest;
import com.minhhieu.userservice.payload.response.JwtResponse;
import com.minhhieu.userservice.payload.response.MessageResponse;

public interface AuthService {
    JwtResponse login(LoginRequest loginRequest);
    MessageResponse signup(SignupRequest signupRequest);
}
