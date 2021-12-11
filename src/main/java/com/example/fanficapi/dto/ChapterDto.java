package com.example.fanficapi.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterDto {

    Long id;
    String name;
    String text;
    String imageReference;
    PublicationDto publication;
}