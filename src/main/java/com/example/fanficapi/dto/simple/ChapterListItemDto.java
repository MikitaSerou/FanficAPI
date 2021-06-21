package com.example.fanficapi.dto.simple;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterListItemDto {

    private Long id;

    private String name;

    private String imageReference;
}
