package com.example.fanficapi.mapper;

import com.example.fanficapi.dto.publication.PreviewPublicationDto;
import com.example.fanficapi.dto.publication.PublicationDto;
import com.example.fanficapi.dto.role.ParentRoleDto;
import com.example.fanficapi.dto.tag.ParentTagDto;
import com.example.fanficapi.dto.theme.ParentThemeDto;
import com.example.fanficapi.dto.theme.ThemeDto;
import com.example.fanficapi.dto.user.UserDto;
import com.example.fanficapi.dto.user.UserShortInfoDto;
import com.example.fanficapi.model.*;
import com.example.fanficapi.payload.SignUpRequest;
import com.example.fanficapi.service.TagService;
import com.example.fanficapi.service.ThemeService;
import com.example.fanficapi.service.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class Mapper {

    @Autowired
    protected TagService tagService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected ThemeService themeService;
    @Autowired
    protected PasswordEncoder encoder;

    @Named(value = "publicationToPreviewDto")
    public abstract PreviewPublicationDto publicationToPreviewDto(Publication publication);

    @Named(value = "publicationToDto")
    public abstract PublicationDto publicationToDto(Publication publication);

    public abstract List<PublicationDto> publicationsListToDto(List<Publication> publications);

    public abstract List<PreviewPublicationDto> publicationsListToPreviewDto(List<Publication> publications);

    @Named(value = "themeToSimpleDto")
    public abstract ParentThemeDto themeToSimpleDto(Theme theme);

    @Mapping(target = "tags", expression = "java(this.tagsSetToSimpleDto(tagService.findByThemeId(theme.getId())))")
    @Mapping(target = "countOfSubscribers", expression = "java(themeService.countSubscribersByThemeId(theme.getId()))")
    public abstract ThemeDto themeToDto(Theme theme);

    public abstract List<ThemeDto> themesListToDto(List<Theme> themes);

    @Mapping(target = "countOfSubscribers", expression = "java(themeService.countSubscribersByThemeId(theme.getId()))")
    public abstract List<ParentThemeDto> themeListToSimpleDto(List<Theme> themes);

    @Named(value = "tagToSimpleDto")
    public abstract ParentTagDto tagToSimpleDto(Tag tag);

    public abstract Set<ParentTagDto> tagsSetToSimpleDto(Set<Tag> tags);

    public abstract ParentRoleDto roleToSimpleDto(Role role);

    public abstract UserShortInfoDto userToShortInfoDto(User user);

    @Mapping(target = "registrationDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "password", expression = "java(encoder.encode(signUpRequest.getPassword()))")
    public abstract User userFromSignUpRequest(SignUpRequest signUpRequest);

    @Named(value = "userToDto")
    public abstract UserDto userToDto(User user);

    public abstract User userDtoToUser(UserDto user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void mergeUsers(User dto, @MappingTarget User entity);
}
