package com.minhhieu.userservice.controller;



import com.minhhieu.userservice.payload.request.LoginRequest;
import com.minhhieu.userservice.payload.request.SignupRequest;
import com.minhhieu.userservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("user")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> SignUp(@RequestBody SignupRequest signupRequest){
        return ResponseEntity.ok(authService.signup(signupRequest));
    }



}
