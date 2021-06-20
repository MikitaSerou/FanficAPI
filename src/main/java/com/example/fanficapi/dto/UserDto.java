package com.example.fanficapi.dto;

import com.example.fanficapi.model.Publication;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String email;

    private Set<RoleDto> roles;

    private Set<ThemeDto> preferences;

    private Set<PublicationDto> publications;

    private Set<PublicationDto> likes;

    private Set<PublicationDto> bookmarks;
}
