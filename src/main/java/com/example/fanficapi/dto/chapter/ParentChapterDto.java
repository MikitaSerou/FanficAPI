package com.example.fanficapi.dto.chapter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ParentChapterDto {

    Long id;
    String name;
    String imageReference;
}
