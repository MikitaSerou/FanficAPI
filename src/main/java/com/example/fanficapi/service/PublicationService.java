package com.example.fanficapi.service;

import com.example.fanficapi.model.Publication;

import java.util.List;

public interface PublicationService {

    List<Publication> findAll();

    Publication findById(Long id);

    Publication findByName(String name);

}
