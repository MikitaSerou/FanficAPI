package com.example.fanficapi.service.impl;

import com.example.fanficapi.model.Chapter;
import com.example.fanficapi.service.ChapterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Override
    public void saveToDB(Chapter chapter) {

    }

    @Override
    public Chapter update(Chapter chapter) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Chapter> findAll() {
        return null;
    }

    @Override
    public Chapter findById(Long id) {
        return null;
    }

    @Override
    public Chapter findByUsername(String name) {
        return null;
    }
}
