package com.gr8erkay.klashaproject.controller;

import com.gr8erkay.klashaproject.model.requestDTO.AuthenticationRequest;
import com.gr8erkay.klashaproject.model.requestDTO.RegisterRequest;
import com.gr8erkay.klashaproject.model.responseDTO.AppResponse;
import com.gr8erkay.klashaproject.model.responseDTO.AuthenticationResponse;
import com.gr8erkay.klashaproject.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> createAccount(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(AppResponse.buildSuccessTxn(authenticationService.createAccount(registerRequest)));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
