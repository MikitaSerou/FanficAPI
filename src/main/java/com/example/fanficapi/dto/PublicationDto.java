package com.example.fanficapi.dto;

import com.example.fanficapi.dto.simple.SimpleChapterDto;
import com.example.fanficapi.dto.simple.SimpleTagDto;
import com.example.fanficapi.dto.simple.SimpleThemeDto;
import com.example.fanficapi.dto.simple.UserShortInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationDto {

    private Long id;

    private String name;

    private String description;

    private UserShortInfoDto author;

    private SimpleThemeDto theme;

    private Set<SimpleChapterDto> chapters;

    private Set<UserShortInfoDto> usersWhoDidBookmark;

    private Set<UserShortInfoDto> usersWhoLiked;

    private Set<SimpleTagDto> tags;
}