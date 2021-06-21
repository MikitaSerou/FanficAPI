package com.example.fanficapi.dto.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreviewPublicationDto { //view to main page

    private Long id;

    private String name;

    private String description;

    private UserShortInfoDto author;

    private SimpleThemeDto theme;

    private Set<SimpleTagDto> tags;
}
