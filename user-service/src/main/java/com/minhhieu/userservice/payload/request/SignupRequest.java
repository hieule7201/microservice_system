package com.minhhieu.userservice.payload.request;


import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String nameUser;
    private Set<String> role;
}
