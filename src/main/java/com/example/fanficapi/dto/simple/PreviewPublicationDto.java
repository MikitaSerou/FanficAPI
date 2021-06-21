package com.example.fanficapi.dto.simple;

import com.example.fanficapi.dto.SimpleThemeDto;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreviewPublicationDto { //view to main page

    private  Long id;

    private String name;

    private String description;

    private UseShortInformationUserDto author;

    private SimpleThemeDto theme;

    private Set<SimpleTagDto> tags;

}
