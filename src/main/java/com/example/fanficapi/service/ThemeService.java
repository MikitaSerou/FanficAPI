package com.example.fanficapi.service;

import com.example.fanficapi.model.Theme;

import java.util.List;

public interface ThemeService {
    void saveToDB(Theme theme);

    List<Theme> findAll();

    Theme findById(Integer id);

    Long countSubscribersByThemeId(Integer themeId);
}
