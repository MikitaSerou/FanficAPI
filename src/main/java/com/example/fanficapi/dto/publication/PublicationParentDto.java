package com.example.fanficapi.dto.publication;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class PublicationParentDto {

    Long id;
    String name;
}
