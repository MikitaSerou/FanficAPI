package com.example.fanficapi.service.impl;

import com.example.fanficapi.exception.ThemeException;
import com.example.fanficapi.model.Theme;
import com.example.fanficapi.repository.ThemeRepository;
import com.example.fanficapi.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Override
    public void saveToDB(Theme theme) {
        themeRepository.save(theme);
    }

    @Override
    public Theme update(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<Theme> findAll() {
        return themeRepository.findAll();
    }

    @Override
    public Theme findById(Integer id) {
        return themeRepository.findById(id)
                .orElseThrow(() -> new ThemeException("Can not find theme with this id: " + id));
    }

    @Override
    public Theme findByUsername(String name) {
        return null;
    }

    @Override
    public Long countSubscribersByThemeId(Integer themeId) {
        return themeRepository.countOfSubscribersByThemeId(themeId);
    }
}
