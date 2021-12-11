package com.example.fanficapi.payload;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {

    @NonNull
    String token;
    String type = "Bearer";
    @NonNull
    String refreshToken;
    @NonNull
    Long id;
    @NonNull
    String username;
    @NonNull
    String email;
    @NonNull
    List<String> roles;

}
