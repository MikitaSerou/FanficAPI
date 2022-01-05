package com.example.fanficapi.dto.publication;

import com.example.fanficapi.dto.chapter.ParentChapterDto;
import com.example.fanficapi.dto.tag.ParentTagDto;
import com.example.fanficapi.dto.theme.SimpleThemeDto;
import com.example.fanficapi.dto.user.UserShortInfoDto;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublicationDto extends PreviewPublicationDto {

    Set<ParentChapterDto> chapters;
    Set<UserShortInfoDto> usersWhoDidBookmark;
    Set<UserShortInfoDto> usersWhoLiked;

    public PublicationDto(Long id, String name, String description,
                          UserShortInfoDto author, SimpleThemeDto theme, Set<ParentTagDto> tags,
                          Set<ParentChapterDto> chapters,
                          Set<UserShortInfoDto> usersWhoDidBookmark,
                          Set<UserShortInfoDto> usersWhoLiked) {
        super(id, name, description, author, theme, tags);
        this.chapters = chapters;
        this.usersWhoDidBookmark = usersWhoDidBookmark;
        this.usersWhoLiked = usersWhoLiked;
    }
}
