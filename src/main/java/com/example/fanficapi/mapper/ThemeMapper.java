package com.example.fanficapi.mapper;

import com.example.fanficapi.dto.theme.ParentThemeDto;
import com.example.fanficapi.dto.theme.ThemeDto;
import com.example.fanficapi.model.Theme;
import com.example.fanficapi.service.TagService;
import com.example.fanficapi.service.ThemeService;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ThemeMapper {

    @Autowired
    protected ThemeService themeService;

    @Autowired
    protected TagService tagService;

    @Autowired
    protected TagMapper tagMapper;

    @Named(value = "themeToSimpleDto")
    public abstract ParentThemeDto themeToSimpleDto(Theme theme);

    @Mapping(target = "tags", expression = "java(tagMapper.tagsSetToSimpleDto(tagService.findByThemeId(theme.getId())))")
    @Mapping(target = "countOfSubscribers", expression = "java(themeService.countSubscribersByThemeId(theme.getId()))")
    public abstract ThemeDto themeToDto(Theme theme);

    public abstract List<ThemeDto> themesListToDto(List<Theme> themes);

    @Mapping(target = "countOfSubscribers", expression = "java(themeService.countSubscribersByThemeId(theme.getId()))")
    public abstract List<ParentThemeDto> themeListToSimpleDto(List<Theme> themes);

}
