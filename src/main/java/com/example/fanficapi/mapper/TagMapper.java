package com.example.fanficapi.mapper;

import com.example.fanficapi.dto.tag.ParentTagDto;
import com.example.fanficapi.model.Tag;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;


@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TagMapper {
    @Named(value = "tagToSimpleDto")
    public abstract ParentTagDto tagToSimpleDto(Tag tag);

    public abstract Set<ParentTagDto> tagsSetToSimpleDto(Set<Tag> tags);

}
