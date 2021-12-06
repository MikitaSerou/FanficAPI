package com.example.fanficapi.dto;

import com.example.fanficapi.dto.simple.PreviewPublicationDto;
import com.example.fanficapi.dto.simple.SimpleRoleDto;
import com.example.fanficapi.dto.simple.SimpleThemeDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;
    String username;
    String email;
    Set<SimpleRoleDto> roles;
    Set<SimpleThemeDto> preferences;
    Set<PreviewPublicationDto> publications;
    Set<PreviewPublicationDto> bookmarks;
}
