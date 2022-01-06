package com.example.fanficapi.controller;


import com.example.fanficapi.dto.publication.PreviewPublicationDto;
import com.example.fanficapi.dto.publication.PublicationDto;
import com.example.fanficapi.mapper.Mapper;
import com.example.fanficapi.service.PublicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/publication")
@Slf4j
public class PublicationController {

    private final PublicationService publicationService;
    private final Mapper mapper;

    @GetMapping("/all")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PublicationDto>> getPublications() {
        List<PublicationDto> publications = mapper.publicationsListToDto(publicationService.findAll());
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    @GetMapping("/preview/{id}")
    public ResponseEntity<PreviewPublicationDto> getSimplePublicationById(@PathVariable("id") Long id) {
        PreviewPublicationDto publication = mapper.publicationToPreviewDto(publicationService.findById(id));
        System.err.println(publication.toString());
        return new ResponseEntity<>(publication, HttpStatus.OK);
    }

    @GetMapping("/page/{id}")
    public ResponseEntity<PublicationDto> getPublicationById(@PathVariable("id") Long id) {
        PublicationDto publication = mapper.publicationToDto(publicationService.findById(id));
        System.err.println(publication.toString());
        return new ResponseEntity<>(publication, HttpStatus.OK);
    }
}
