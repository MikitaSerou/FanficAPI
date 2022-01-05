package com.example.fanficapi.controller;


import com.example.fanficapi.dto.publication.PublicationDto;
import com.example.fanficapi.dto.publication.PreviewPublicationDto;
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

    @GetMapping("/all")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PublicationDto>> getPublications() {
        List<PublicationDto> publications = publicationService.getAllDto();
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    @GetMapping("/preview/{id}")
    public ResponseEntity<PreviewPublicationDto> getSimplePublicationById(@PathVariable("id") Long id) {
        PreviewPublicationDto publication = publicationService.getSimpleDtoById(id);
        System.err.println(publication.toString());
        return new ResponseEntity<>(publication, HttpStatus.OK);
    }

    @GetMapping("/page/{id}")
    public ResponseEntity<PublicationDto> getPublicationById(@PathVariable("id") Long id) {
        PublicationDto publication = publicationService.getDtoById(id);
        System.err.println(publication.toString());
        return new ResponseEntity<>(publication, HttpStatus.OK);
    }
}
