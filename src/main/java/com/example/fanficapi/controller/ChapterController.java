package com.example.fanficapi.controller;

import com.example.fanficapi.mapper.Mapper;
import com.example.fanficapi.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;
    private final Mapper mapper;
}


