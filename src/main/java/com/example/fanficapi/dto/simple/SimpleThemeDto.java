package com.example.fanficapi.dto.simple;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleThemeDto {

    private Integer id;

    private String name;

    private String imageUrl;
}
