package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.dto.AuthenticationRequest;
import com.vandidroid.digitalnomaddestinations.model.dto.AuthenticationResponse;
import com.vandidroid.digitalnomaddestinations.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://travel.dinodev.hu", "http://localhost:3000"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "The Authentication API", description = "The Authentication API")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final AuthenticationService authenticationService;

    @PostMapping("/token")
    public AuthenticationResponse token(@RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        authenticationManager.authenticate(authentication);

        return AuthenticationResponse.builder().token(authenticationService.generateToken(authentication)).build();
    }
}
