package com.example.fanficapi.service;

import com.example.fanficapi.dto.ThemeDto;
import com.example.fanficapi.dto.simple.SimpleThemeDto;
import com.example.fanficapi.exception.ThemeException;
import com.example.fanficapi.model.Theme;
import com.example.fanficapi.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeService extends AbstractService<Theme, Integer, SimpleThemeDto, ThemeDto> {

    private final ThemeRepository themeRepository;

    @Override
    public void saveToDB(Theme object) {

    }

    @Override
    public Theme update(Theme object) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<Theme> findAll() {
        return null;
    }

    public Theme findById(Integer id) {
        return themeRepository.findById(id)
                .orElseThrow(() -> new ThemeException("Can not find theme with this id: " + id));
    }

    @Override
    public List<ThemeDto> getAllDto() {
        return null;
    }

    @Override
    public SimpleThemeDto getSimpleDtoById(Integer id) {
        return mapper.themeToSimpleDto(findById(id));
    }

    @Override
    public ThemeDto getDtoById(Integer id) {
        return mapper.themeToDto(findById(id));
    }

    @Override
    public Theme findByName(String name) {
        return null;
    }
}
