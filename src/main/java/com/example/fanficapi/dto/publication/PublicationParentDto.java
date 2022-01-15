package com.example.fanficapi.dto.publication;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class PublicationParentDto {

    Long id;
    String name;
}
