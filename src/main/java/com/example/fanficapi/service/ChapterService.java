package com.example.fanficapi.service;

import com.example.fanficapi.model.Chapter;

import java.util.List;

public interface ChapterService {

    void saveToDB(Chapter object);

    Chapter update(Chapter object);

    void deleteById(Long id);

    List<Chapter> findAll();

    Chapter findById(Long id);

    Chapter findByUsername(String name);

}

