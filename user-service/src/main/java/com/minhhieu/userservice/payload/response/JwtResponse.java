package com.minhhieu.userservice.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private long idUser;
    private String username;
    private String nameUser;
    private List<String> roles;

    public JwtResponse(String accessToken, Long idUser, String username, String nameUser, List<String> roles) {
        this.token = accessToken;
        this.idUser = idUser;
        this.username = username;
        this.nameUser = nameUser;
        this.roles = roles;
    }
}
