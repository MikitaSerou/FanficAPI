package com.example.fanficapi.dto;

import com.example.fanficapi.dto.simple.PreviewPublicationDto;
import com.example.fanficapi.dto.simple.UserShortInfoDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThemeDto {

    Integer id;
    String name;
    String imageUrl;
    Set<PreviewPublicationDto> publications;
    Set<UserShortInfoDto> subscribers;
}