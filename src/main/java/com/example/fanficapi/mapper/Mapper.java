package com.example.fanficapi.mapper;

import com.example.fanficapi.dto.publication.PublicationDto;
import com.example.fanficapi.dto.role.ParentRoleDto;
import com.example.fanficapi.dto.role.RoleDto;
import com.example.fanficapi.dto.tag.ParentTagDto;
import com.example.fanficapi.dto.theme.ThemeDto;
import com.example.fanficapi.dto.user.UserDto;
import com.example.fanficapi.dto.publication.PreviewPublicationDto;
import com.example.fanficapi.dto.theme.SimpleThemeDto;
import com.example.fanficapi.dto.user.UserShortInfoDto;
import com.example.fanficapi.model.*;
import org.mapstruct.*;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Mapper {

    @Named(value = "publicationToPreviewDto")
    PreviewPublicationDto publicationToPreviewDto(Publication publication);

    PublicationDto publicationToDto(Publication publication);

    List<PublicationDto> publicationsListToDto(List<Publication> publications);

    List<PreviewPublicationDto> publicationsListPreviewToDto(List<PreviewPublicationDto> publications);

    @Named(value = "themeToSimpleDto")
    SimpleThemeDto themeToSimpleDto(Theme theme);

    ThemeDto themeToDto(Theme theme);

    List<ThemeDto> themesListToto(List<Theme> themes);

    List<SimpleThemeDto> themeListToSimpleDto(List<Theme> themes);

    @Named(value = "tagToSimpleDto")
    ParentTagDto tagToSimpleDto(Tag tag);

    UserShortInfoDto userToShortInfoDto(User user);

    @Named(value = "userToDto")
    UserDto userToDto(User user);

    ParentRoleDto roleToSimpleDto(Role role);

    @Named(value = "toRoleDto")
    RoleDto toRoleDto(Role role);

    User userDtoToUser(UserDto user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromUserSimpleDto(UserShortInfoDto dto, @MappingTarget User entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromUserDto(UserDto dto, @MappingTarget User entity);
}
