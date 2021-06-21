package com.example.fanficapi.dto.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserShortInfoDto {

    private Long id;

    private String username;

    private String email;
}
