package com.example.fanficapi.controller;

import com.example.fanficapi.dto.ThemeDto;
import com.example.fanficapi.dto.simple.SimpleThemeDto;
import com.example.fanficapi.service.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theme")
@Slf4j
public class ThemeController {

    @Autowired
    private ThemeService themeService;


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
}
