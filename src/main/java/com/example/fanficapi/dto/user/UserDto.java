package com.example.fanficapi.dto.user;

import com.example.fanficapi.dto.publication.PreviewPublicationDto;
import com.example.fanficapi.dto.role.ParentRoleDto;
import com.example.fanficapi.dto.theme.SimpleThemeDto;
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
public class UserDto extends UserShortInfoDto {

    Set<ParentRoleDto> roles;
    Set<SimpleThemeDto> preferences;
    Set<PreviewPublicationDto> publications;
    Set<PreviewPublicationDto> bookmarks;

    public UserDto(Long id, String username, String email, Set<ParentRoleDto> roles,
                   Set<SimpleThemeDto> preferences,
                   Set<PreviewPublicationDto> publications,
                   Set<PreviewPublicationDto> bookmarks) {
        super(id, username, email);
        this.roles = roles;
        this.preferences = preferences;
        this.publications = publications;
        this.bookmarks = bookmarks;
    }
}
