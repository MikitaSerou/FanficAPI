package com.example.fanficapi.dto.chapter;

import com.example.fanficapi.dto.publication.PublicationDto;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterDto extends ParentChapterDto {

    private String text;
    private PublicationDto publication;

    public ChapterDto(Long id, String name, String imageReference, String text, PublicationDto publication) {
        super(id, name, imageReference);
        this.text = text;
        this.publication = publication;
    }
}
