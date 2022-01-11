package com.example.fanficapi.service;

import com.example.fanficapi.model.Theme;

import java.util.List;

public interface ThemeService {

    void saveToDB(Theme theme);

    Theme update(Theme theme);

    void deleteById(Integer id);

    List<Theme> findAll();

    Theme findById(Integer id);

    Theme findByUsername(String name);

    Long countSubscribersByThemeId(Integer themeId);
}
