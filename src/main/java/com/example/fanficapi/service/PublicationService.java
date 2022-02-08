package com.example.fanficapi.service;

import com.example.fanficapi.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublicationService {

    Page<Publication> findAll(Pageable pageable);

    Publication findById(Long id);

    Publication findByName(String name);

    Page<Publication> findAllByThemeId(Integer themeId, Pageable pageable);
}
