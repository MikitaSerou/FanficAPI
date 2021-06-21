package com.example.fanficapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDto {

    private Long id;

    private String name;

    private String text;

    private String imageReference;

    private PublicationDto publication;
}