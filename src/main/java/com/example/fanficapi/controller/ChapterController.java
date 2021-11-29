package com.example.fanficapi.controller;

import com.example.fanficapi.service.ChaptersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChapterController {

    private final ChaptersService chaptersService;

}
