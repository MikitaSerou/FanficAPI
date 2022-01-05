package com.example.fanficapi.dto.theme;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleThemeDto {

    Integer id;
    String name;
    String imageUrl;
}
