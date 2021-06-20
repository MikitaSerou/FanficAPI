package com.example.fanficapi.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThemeDto {

    private Integer id;

    private String name;

    private String imageUrl;

    private Set<PublicationDto> publications;

    private Set<UserDto> usersWhoPreference;
}
