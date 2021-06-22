package com.example.fanficapi.dto.simple;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleChapterDto {

    private Long id;

    private String name;

    private String imageReference;
}
