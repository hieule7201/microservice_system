package com.minhhieu.userservice.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minhhieu.userservice.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long idUser;

    private String username;

    private String nameUser;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;
    public UserDetailsImpl(Long idUser, String username, String nameUser, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.idUser = idUser;
        this.username = username;
        this.nameUser = nameUser;
        this.password = password;
        this.authorities = authorities;
    }
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNameRole().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getIdUser(),
                user.getUsername(),
                user.getNameUser(),
                user.getPassword(),
                authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getNameUser() {
        return nameUser;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(idUser, user.idUser);
    }
}
