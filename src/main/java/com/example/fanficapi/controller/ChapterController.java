package com.example.fanficapi.controller;

import com.example.fanficapi.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;
}


