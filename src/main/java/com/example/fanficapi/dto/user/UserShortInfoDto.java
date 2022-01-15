package com.example.fanficapi.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class UserShortInfoDto {

    Long id;
    String username;
    String email;
}
