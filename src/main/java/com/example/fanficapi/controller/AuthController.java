package com.example.fanficapi.controller;

import com.example.fanficapi.exception.TokenRefreshException;
import com.example.fanficapi.model.User;
import com.example.fanficapi.payload.*;
import com.example.fanficapi.security.JwtResponse;
import com.example.fanficapi.security.JwtUtils;
import com.example.fanficapi.security.model.RefreshToken;
import com.example.fanficapi.security.service.AuthenticationService;
import com.example.fanficapi.security.service.RefreshTokenService;
import com.example.fanficapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600) //TODO Check in case when bean in main class wil not work
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {
        setUsernameByEmailIfItNullInRequest(signInRequest);
        Authentication authentication = authenticationService.getAuthenticationBySignInRequest(signInRequest);
        authenticationService.setAuthenticationInContext(authentication);
        String accessToken = jwtUtils.generateJwtToken((UserDetailsImpl) authentication.getPrincipal());
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roleNames = authenticationService.getRoleNames(userDetails.getAuthorities());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), roleNames));
    }

    //if email is null in request, then set username by email
    private void setUsernameByEmailIfItNullInRequest(SignInRequest signInRequest) {
        String usernameOrEmail = signInRequest.getUsername();
        if (!userService.existsByUsername(usernameOrEmail)) {
            signInRequest.setUsername(userService.getUsernameByEmail(usernameOrEmail));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) throws TokenRefreshException {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUserName(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("Passwords do not match!"));
        }
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("Username is already taken!"));
        }
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("Email is already in use!"));
        }
        if (signUpRequest.getBirthDate().isAfter(LocalDate.now().minusYears(16))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("You must be at least 16 years old!"));
        }
        User userForSave = userService.getUserFromSignUpRequest(signUpRequest);
        userForSave.setPassword(encoder.encode(signUpRequest.getPassword()));
        userService.saveToDB(userForSave);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
