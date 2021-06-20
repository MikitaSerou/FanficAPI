package com.example.fanficapi.service;

import com.example.fanficapi.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChaptersService {

    @Autowired
    private ChapterRepository chapterRepository;
}
