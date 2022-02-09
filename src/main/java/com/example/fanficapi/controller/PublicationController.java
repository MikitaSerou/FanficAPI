package com.example.fanficapi.controller;


import com.example.fanficapi.dto.publication.PreviewPublicationDto;
import com.example.fanficapi.dto.publication.PublicationDto;
import com.example.fanficapi.mapper.PublicationMapper;
import com.example.fanficapi.model.Publication;
import com.example.fanficapi.service.PublicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/publication")
@Slf4j
public class PublicationController {

    private final PublicationService publicationService;
    private final PublicationMapper mapper;

    @GetMapping
    public ResponseEntity<PageImpl<PublicationDto>> getAllPublications(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size,
                                                                       @RequestParam(defaultValue = "false") boolean descSort) {
        Pageable paging = PageRequest.of(page, size, setDescendingSorting(descSort));
        Page<Publication> entityPage = publicationService.findAll(paging);
        List<PublicationDto> publicationsDtos = mapper.publicationsPageToDto(entityPage.getContent());
        return new ResponseEntity<>(new PageImpl<>(publicationsDtos, paging, entityPage.getTotalElements()), HttpStatus.OK);
    }

    private Sort setDescendingSorting(boolean descSort) {
        return descSort ? Sort.by("id").descending() : Sort.unsorted();
    }

    @GetMapping("/preview/{id}")
    public ResponseEntity<PreviewPublicationDto> getSimplePublicationById(@PathVariable("id") Long id) {
        PreviewPublicationDto publication = mapper.publicationToPreviewDto(publicationService.findById(id));
        return new ResponseEntity<>(publication, HttpStatus.OK);
    }

    @GetMapping("/theme/{id}")
    public ResponseEntity<PageImpl<PreviewPublicationDto>> getSimplePublicationsByThemeId(@PathVariable("id") Integer themeId,
                                                                                          @RequestParam(defaultValue = "0") int page,
                                                                                          @RequestParam(defaultValue = "10") int size,
                                                                                          @RequestParam(defaultValue = "false") boolean descSort) {
        Pageable paging = PageRequest.of(page, size, setDescendingSorting(descSort));
        Page<Publication> entityPage = publicationService.findAllByThemeId(themeId, paging);
        List<PreviewPublicationDto> publicationsDtos = mapper.publicationsListToPreviewDto(entityPage.getContent());
        return new ResponseEntity<>(new PageImpl<>(publicationsDtos, paging, entityPage.getTotalElements()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDto> getPublicationById(@PathVariable("id") Long id) {
        PublicationDto publication = mapper.publicationToDto(publicationService.findById(id));
        return new ResponseEntity<>(publication, HttpStatus.OK);
    }
}
