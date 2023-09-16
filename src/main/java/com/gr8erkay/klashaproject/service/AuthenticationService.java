package com.gr8erkay.klashaproject.service;


import com.gr8erkay.klashaproject.entity.User;
import com.gr8erkay.klashaproject.exception.ResourceCreationException;
import com.gr8erkay.klashaproject.model.requestDTO.AuthenticationRequest;
import com.gr8erkay.klashaproject.model.requestDTO.EmailValidator;
import com.gr8erkay.klashaproject.model.requestDTO.RegisterRequest;
import com.gr8erkay.klashaproject.model.responseDTO.AuthenticationResponse;
import com.gr8erkay.klashaproject.repository.UserRepository;
import com.gr8erkay.klashaproject.utils.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final EmailValidator emailValidator;





    public AuthenticationResponse createAccount(RegisterRequest registerRequest) {
        boolean isValidEmail = emailValidator.test(registerRequest.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("Email is not Valid");
        }
        Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());
        if(existingUser.isPresent()){
            throw new ResourceCreationException("User already existing");
        }
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setUsername(registerRequest.getUsername());
        user.setFirstname(registerRequest.getFirstname());
        user.setLastname(registerRequest.getLastname());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
