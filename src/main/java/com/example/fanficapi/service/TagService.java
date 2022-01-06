package com.example.fanficapi.service;

import com.example.fanficapi.model.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {

    void saveToDB(Tag tag);

    Tag update(Tag tag);

    void deleteById(Long id);

    List<Tag> findAll();

    Tag findById(Long id);

    Tag findByUsername(String name);

    Set<Tag> findByThemeId(Integer themeId);
}
