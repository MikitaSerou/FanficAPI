package com.example.fanficapi.dto.publication;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class PublicationParentDto {

    Long id;
    String name;
    LocalDate creationDate;
    LocalDate updateDate;

    public PublicationParentDto(Long id, String name) {
        this.id = id;
        this.name = name;
        this.creationDate = LocalDate.now();
        this.updateDate = LocalDate.now();
    }
}
