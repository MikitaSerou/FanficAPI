package com.example.fanficapi.controller;

import com.example.fanficapi.dto.theme.ThemeDto;
import com.example.fanficapi.dto.theme.SimpleThemeDto;
import com.example.fanficapi.service.ThemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/theme")
@Slf4j
public class ThemeController {

    private final ThemeService themeService;

    @GetMapping("/preview/{id}")
    public ResponseEntity<SimpleThemeDto> getSimplePublicationById(@PathVariable("id") Integer id) {
        SimpleThemeDto publication = themeService.getSimpleDtoById(id);
        return new ResponseEntity<>(publication, HttpStatus.OK);
    }

    @GetMapping("/page/{id}")
    public ResponseEntity<ThemeDto> getPublicationById(@PathVariable("id") Integer id) {
        ThemeDto publication = themeService.getDtoById(id);
        return new ResponseEntity<>(publication, HttpStatus.OK);
    }

    @GetMapping("/allPreviews")
    public ResponseEntity<List<SimpleThemeDto>> getAll() {
        List<SimpleThemeDto> list = themeService.getAllPreviewsDto();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
