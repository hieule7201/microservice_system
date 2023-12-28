package com.minhhieu.userservice.controller;


import com.minhhieu.userservice.model.User;
import com.minhhieu.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("details/{idUser}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable long idUser){
        return ResponseEntity.ok(userService.getUserById(idUser));
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin (){
        return "admin";
    }
}
