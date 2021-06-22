package com.example.fanficapi.dto;

import com.example.fanficapi.dto.simple.PreviewPublicationDto;
import com.example.fanficapi.dto.simple.SimpleRoleDto;
import com.example.fanficapi.dto.simple.SimpleThemeDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String email;

    private Set<SimpleRoleDto> roles;

    private Set<SimpleThemeDto> preferences;

    private Set<PreviewPublicationDto> publications;

    private Set<PreviewPublicationDto> bookmarks;
}
