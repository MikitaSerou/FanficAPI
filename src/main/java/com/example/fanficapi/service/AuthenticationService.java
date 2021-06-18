package com.example.fanficapi.service;

import com.example.fanficapi.pojo.SignInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public Authentication getAuthenticationBySignInRequest(SignInRequest signInRequest){
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

    }

    public void setAuthenticationInContext(Authentication authentication){
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public List<String> getRoleNames(Collection<? extends GrantedAuthority> roles) {
        return roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}
