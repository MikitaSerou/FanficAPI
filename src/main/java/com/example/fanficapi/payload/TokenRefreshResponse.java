package com.example.fanficapi.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenRefreshResponse {

    String tokenType = "Bearer";
    @NonNull
    String accessToken;
    @NonNull
    String refreshToken;

}
