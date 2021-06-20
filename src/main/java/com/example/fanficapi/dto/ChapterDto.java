package com.example.fanficapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDto {

    private Long id;

    private String name;

    private String text;

    private String imageReference;

    private Long publicationId;
}
