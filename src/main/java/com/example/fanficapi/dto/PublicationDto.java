package com.example.fanficapi.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicationDto {

    private  Long id;

    private String name;

    private String description;

    private UserDto author;

    private ThemeDto theme;

    private Set<ChapterDto> chapters;

    private Set<UserDto> usersWhoDidBookmark;

    private Set<UserDto> usersWhoLiked;

    private Set<TagDto> tags;

}
