package com.example.fanficapi.controller;

import com.example.fanficapi.mapper.Mapper;
import com.example.fanficapi.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
@Slf4j
public class TagController {

    private final TagService tagService;
    private final Mapper mapper;
}
