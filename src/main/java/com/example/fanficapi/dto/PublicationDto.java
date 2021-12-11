package com.example.fanficapi.dto;

import com.example.fanficapi.dto.simple.SimpleChapterDto;
import com.example.fanficapi.dto.simple.SimpleTagDto;
import com.example.fanficapi.dto.simple.SimpleThemeDto;
import com.example.fanficapi.dto.simple.UserShortInfoDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublicationDto {

    Long id;
    String name;
    String description;
    UserShortInfoDto author;
    SimpleThemeDto theme;
    Set<SimpleChapterDto> chapters;
    Set<UserShortInfoDto> usersWhoDidBookmark;
    Set<UserShortInfoDto> usersWhoLiked;
    Set<SimpleTagDto> tags;
}