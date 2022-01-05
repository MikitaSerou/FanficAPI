package com.example.fanficapi.dto.tag;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ParentTagDto {

    Long id;
    String name;
}
