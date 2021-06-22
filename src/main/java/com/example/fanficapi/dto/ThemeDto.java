package com.example.fanficapi.dto;

import com.example.fanficapi.dto.simple.PreviewPublicationDto;
import com.example.fanficapi.dto.simple.UserShortInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ThemeDto {

    private Integer id;

    private String name;

    private String imageUrl;

    private Set<PreviewPublicationDto> publications;

    private Set<UserShortInfoDto> subscribers;
}