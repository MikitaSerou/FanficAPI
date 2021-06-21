package com.example.fanficapi.mapper;

import com.example.fanficapi.dto.*;
import com.example.fanficapi.dto.simple.ChapterListItemDto;
import com.example.fanficapi.dto.simple.PreviewPublicationDto;
import com.example.fanficapi.dto.simple.SimpleTagDto;
import com.example.fanficapi.dto.simple.UseShortInformationUserDto;
import com.example.fanficapi.model.*;
import org.mapstruct.Mapping;

@org.mapstruct.Mapper(componentModel = "spring"/*, unmappedTargetPolicy = ReportingPolicy.IGNORE*/)
public interface Mapper {

//    SimpleTagDto tagToDto(Tag tag);
//
//    @Mapping(target = "publications", ignore = true)
//    Tag tagDtoToTag(SimpleTagDto dto);

 //   @Mapping(target = "publication", ignore = true)
    ChapterListItemDto chapterToDto(Chapter chapter);

    @Mapping(target = "publication", ignore = true)
    Chapter chapterDtoToChapter(ChapterListItemDto dto);

    RoleDto roleToDto(Role role);

    Role roleDtoToRole(RoleDto dto);

    PreviewPublicationDto publicationToSimpleDto(Publication publication);

    @Mapping(target = "usersWhoDidBookmark", ignore = true)
    @Mapping(target = "usersWhoLiked", ignore = true)
    Publication simplePublicationDtoToPublication(PreviewPublicationDto dto);

   //@Mapping(target = "publications", ignore = true)
    SimpleTagDto tagToSimpleDto(Tag tag);

    @Mapping(target = "publications", ignore = true)
    Tag simpleTagDtoToTag(SimpleTagDto dto);

    //@Mapping(target = "usersWhoPreference", ignore = true)
    SimpleThemeDto themeToSimpleDto(Theme theme);

    @Mapping(target = "usersWhoPreference", ignore = true)
    Theme simpleThemeDtoToTheme(SimpleThemeDto dto);


    UseShortInformationUserDto userToDto(User user);

    User userDtoToUser(User user);
}
