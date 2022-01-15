package com.example.fanficapi.service.impl;


import com.example.fanficapi.exception.PublicationException;
import com.example.fanficapi.model.Publication;
import com.example.fanficapi.repository.PublicationRepository;
import com.example.fanficapi.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;


    @Override
    public List<Publication> findAll() {
        return publicationRepository.findAll();
    }


    @Override
    public Publication findById(Long id) {
        return publicationRepository.findById(id)
                .orElseThrow(
                        () -> new PublicationException("Publication with this id (" + id + ") was not found"));
    }

    @Override
    public Publication findByName(String name) {
        return publicationRepository.findByName(name)
                .orElseThrow(
                        () -> new PublicationException("Publication with this name (" + name + ") was not found"));
    }
}

