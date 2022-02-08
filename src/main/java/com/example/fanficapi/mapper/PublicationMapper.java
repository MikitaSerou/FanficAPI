package com.example.fanficapi.mapper;

import com.example.fanficapi.dto.publication.PreviewPublicationDto;
import com.example.fanficapi.dto.publication.PublicationDto;
import com.example.fanficapi.model.Publication;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PublicationMapper {

    @Named(value = "publicationToPreviewDto")
    public abstract PreviewPublicationDto publicationToPreviewDto(Publication publication);

    @Named(value = "publicationToDto")
    public abstract PublicationDto publicationToDto(Publication publication);

    public abstract List<PublicationDto> publicationsListToDto(List<Publication> publications);

    public abstract List<PublicationDto> publicationsPageToDto(List<Publication> publications);

    public abstract List<PreviewPublicationDto> publicationsListToPreviewDto(List<Publication> publications);


}
