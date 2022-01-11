package com.example.fanficapi.dto.theme;

import com.example.fanficapi.dto.publication.PublicationParentDto;
import com.example.fanficapi.dto.tag.ParentTagDto;
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
public class ThemeDto extends ParentThemeDto {

    Set<PublicationParentDto> publications;
    Set<ParentTagDto> tags;


    public ThemeDto(Integer id, String name, String imageUrl, Set<PublicationParentDto> publications,
                    Long countOfSubscribers, Set<ParentTagDto> tags) {
        super(id, name, imageUrl, countOfSubscribers);
        this.publications = publications;
        this.tags = tags;
    }
}
