package com.example.fanficapi.payload;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    String username;

    @NotBlank
    @Size(max = 50)
    @Email
    String email;

    @NotBlank
    @Size(min = 6, max = 40)
    String password;

    Set<String> roles;
}
