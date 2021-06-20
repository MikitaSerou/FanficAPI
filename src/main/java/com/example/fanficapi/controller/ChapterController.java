package com.example.fanficapi.controller;

import com.example.fanficapi.service.ChaptersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ChapterController {

    @Autowired
    private ChaptersService chaptersService;
}
