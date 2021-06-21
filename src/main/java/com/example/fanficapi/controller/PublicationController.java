package com.example.fanficapi.controller;


import com.example.fanficapi.dto.simple.PreviewPublicationDto;
import com.example.fanficapi.model.Publication;
import com.example.fanficapi.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publication")
@Slf4j
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @GetMapping("/all")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Publication>> getPublications() {
        List<Publication> publications = publicationService.findAll();
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PreviewPublicationDto> getSimplePublicationById(@PathVariable("id") Long id){
        PreviewPublicationDto publication = publicationService.getSimpleById(id);
        System.err.println(publication.toString());
        return new ResponseEntity<>(publication, HttpStatus.OK);
    }
}
