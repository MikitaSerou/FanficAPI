package com.example.fanficapi.dto;

import com.example.fanficapi.dto.simple.PreviewPublicationDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagDto {

    Long id;
    String name;
    Set<PreviewPublicationDto> publications;
}