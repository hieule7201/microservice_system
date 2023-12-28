package com.minhhieu.userservice.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRole;

    @Enumerated(EnumType.STRING)
    private ERole nameRole;
}
