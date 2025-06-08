package com.devtiro.Blog.Platform.controller;

import com.devtiro.Blog.Platform.domain.dtos.LoginRequest;
import com.devtiro.Blog.Platform.domain.dtos.LoginResponse;
import com.devtiro.Blog.Platform.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        UserDetails userDetails = authenticationService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        String tokenValue = authenticationService.generateToken(userDetails);

        LoginResponse loginResponse = LoginResponse.builder() //What does the builder method do, also its annotation
                .token(tokenValue)
                .expiresIn(120)
                .build();
        return ResponseEntity.ok(loginResponse);
    }

}
