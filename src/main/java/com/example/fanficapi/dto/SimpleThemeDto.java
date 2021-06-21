package com.example.fanficapi.dto;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleThemeDto {

    private Integer id;

    private String name;

    private String imageUrl;

   // private Set<SimplePublicationDto> publications;

}
