package com.example.fanficapi.controller;

import com.example.fanficapi.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ThemeController {

    @Autowired
    private ThemeService themeService;
}
