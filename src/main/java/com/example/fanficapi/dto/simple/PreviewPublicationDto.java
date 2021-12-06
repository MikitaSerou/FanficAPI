package com.example.fanficapi.dto.simple;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PreviewPublicationDto { //view to main page

    Long id;
    String name;
    String description;
    UserShortInfoDto author;
    SimpleThemeDto theme;
    Set<SimpleTagDto> tags;
}
