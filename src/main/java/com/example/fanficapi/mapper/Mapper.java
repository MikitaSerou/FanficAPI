package com.example.fanficapi.mapper;

import com.example.fanficapi.dto.TagDto;
import com.example.fanficapi.model.Tag;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    TagDto tagToDto(Tag tag);

    Tag tagDtoToTag(TagDto tagDto);
}
