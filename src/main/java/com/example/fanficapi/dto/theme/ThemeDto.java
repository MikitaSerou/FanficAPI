package com.example.fanficapi.dto.theme;

import com.example.fanficapi.dto.publication.PreviewPublicationDto;
import com.example.fanficapi.dto.user.UserShortInfoDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThemeDto {

    Integer id;
    String name;
    String imageUrl;
    Set<PreviewPublicationDto> publications;
    Set<UserShortInfoDto> subscribers;
}
