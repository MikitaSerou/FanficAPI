package com.example.fanficapi.service;

import com.example.fanficapi.model.Tag;

import java.util.Set;

public interface TagService {

    Tag findById(Long id);

    Set<Tag> findByThemeId(Integer themeId);
}
