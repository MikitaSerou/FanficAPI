package com.example.fanficapi.dto;

import com.example.fanficapi.dto.simple.PreviewPublicationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {

    private Long id;

    private String name;

    private Set<PreviewPublicationDto> publications;
}