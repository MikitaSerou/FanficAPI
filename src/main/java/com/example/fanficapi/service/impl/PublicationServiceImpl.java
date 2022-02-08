package com.example.fanficapi.service.impl;


import com.example.fanficapi.exception.PublicationException;
import com.example.fanficapi.model.Publication;
import com.example.fanficapi.repository.PublicationRepository;
import com.example.fanficapi.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    @Override
    public Page<Publication> findAll(Pageable pageable) {
        return publicationRepository.findAll(pageable);
    }

    @Override
    public Publication findById(Long id) {
        return publicationRepository.findById(id)
                .orElseThrow(
                        () -> new PublicationException("Publication with this id (" + id + ") was not found"));
    }

    @Override
    public Publication findByName(String name) { //TODO %LIKE%
        return publicationRepository.findByName(name)
                .orElseThrow(
                        () -> new PublicationException("Publication with this name (" + name + ") was not found"));
    }

    @Override
    public Page<Publication> findAllByThemeId(Integer themeId, Pageable pageable) {
        return publicationRepository.findAllByThemeId(themeId, pageable);
    }
}

