package com.example.fanficapi.payload;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenRefreshRequest {
    @NotBlank
    String refreshToken;
}
