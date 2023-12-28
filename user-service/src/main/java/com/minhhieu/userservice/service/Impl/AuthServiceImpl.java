package com.minhhieu.userservice.service.Impl;

import com.minhhieu.userservice.model.ERole;
import com.minhhieu.userservice.model.Role;
import com.minhhieu.userservice.model.User;
import com.minhhieu.userservice.payload.request.LoginRequest;
import com.minhhieu.userservice.payload.request.SignupRequest;
import com.minhhieu.userservice.payload.response.JwtResponse;
import com.minhhieu.userservice.payload.response.MessageResponse;
import com.minhhieu.userservice.repository.RoleRepository;
import com.minhhieu.userservice.repository.UserRepository;
import com.minhhieu.userservice.security.jwt.JwtUtil;
import com.minhhieu.userservice.security.service.UserDetailsImpl;
import com.minhhieu.userservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtil;
    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getIdUser(),
                userDetails.getUsername(),
                userDetails.getNameUser(),
                roles);
    }

    @Override
    public MessageResponse signup(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            System.out.println(userRepository.existsByUsername(signupRequest.getUsername()));
            return new MessageResponse("Error: Username is already taken!");
        }

        User user = new User(signupRequest.getUsername(),
                signupRequest.getNameUser(),
                encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByNameRole(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
        else
        {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByNameRole(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByNameRole(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByNameRole(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return new MessageResponse("User registered successfully!");
    }
}
