package com.example.fanficapi.dto.publication;

import com.example.fanficapi.dto.tag.ParentTagDto;
import com.example.fanficapi.dto.theme.SimpleThemeDto;
import com.example.fanficapi.dto.user.UserShortInfoDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class PreviewPublicationDto extends PublicationParentDto { //view to main page

    protected String description;
    protected UserShortInfoDto author;
    protected SimpleThemeDto theme;
    protected Set<ParentTagDto> tags;

    public PreviewPublicationDto(Long id, String name, String description, UserShortInfoDto author,
                                 SimpleThemeDto theme, Set<ParentTagDto> tags) {
        super(id, name);
        this.description = description;
        this.author = author;
        this.theme = theme;
        this.tags = tags;
    }
}
