package com.example.fanficapi.controller;

import com.example.fanficapi.jwt.JwtUtils;
import com.example.fanficapi.model.User;
import com.example.fanficapi.pojo.*;
import com.example.fanficapi.service.AuthenticationService;
import com.example.fanficapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600) //TODO Check in case when bean in main class wil not work
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {
        Authentication authentication = authenticationService.getAuthenticationBySignInRequest(signInRequest);
        authenticationService.setAuthenticationInContext(authentication);
        String jwt = jwtUtils.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roleNames = authenticationService.getRoleNames(userDetails.getAuthorities());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), roleNames));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        User userForSave = userService.getUserFromSignUpRequest(signUpRequest);
        userForSave.setPassword(encoder.encode(signUpRequest.getPassword()));
        userService.saveToDB(userForSave);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
