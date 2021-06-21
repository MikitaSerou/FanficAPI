package com.example.fanficapi.mapper;

import com.example.fanficapi.dto.PublicationDto;
import com.example.fanficapi.dto.RoleDto;
import com.example.fanficapi.dto.ThemeDto;
import com.example.fanficapi.dto.UserDto;
import com.example.fanficapi.dto.simple.*;
import com.example.fanficapi.model.*;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring"/*, unmappedTargetPolicy = ReportingPolicy.IGNORE*/)
public interface Mapper {

    PreviewPublicationDto publicationToPreviewDto(Publication publication);

    PublicationDto publicationToDto(Publication publication);

    List<PublicationDto> publicationsListToDto(List<Publication> publications);

    List<PreviewPublicationDto> publicationsListPreviewToDto(List<PreviewPublicationDto> publications);

    SimpleThemeDto themeToSimpleDto(Theme theme);

    ThemeDto themeToDto(Theme theme);

    List<ThemeDto> themesListToto(List<Theme> themes);

    List<SimpleThemeDto> themeListToSimpleDto(List<Theme> themes);

    SimpleTagDto tagToSimpleDto(Tag tag);

    UserShortInfoDto userToShortInfoDto(User user);

    UserDto userToDto(User user);

    SimpleRoleDto roleToSimpleDto(Role role);

    RoleDto toRoleDto(Role role);
}
