package com.example.fanficapi.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {

    Long id;

    private  String name;

    private Set<PublicationDto> publications;


}
