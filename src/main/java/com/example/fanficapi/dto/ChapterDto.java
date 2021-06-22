package com.example.fanficapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChapterDto {

    private Long id;

    private String name;

    private String text;

    private String imageReference;

    private PublicationDto publication;
}