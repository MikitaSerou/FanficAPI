package com.example.fanficapi.dto.tag;

import com.example.fanficapi.dto.publication.PreviewPublicationDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@EqualsAndHashCode(callSuper=true)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagDto extends ParentTagDto{

    Set<PreviewPublicationDto> publications;

    public TagDto(Long id, String name,Set<PreviewPublicationDto> publications) {
        super(id, name);
        this.publications = publications;
    }
}
