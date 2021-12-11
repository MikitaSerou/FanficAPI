package com.example.fanficapi.payload;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignInRequest {

    @NotBlank
    String username;

    @NotBlank
    String password;
}
