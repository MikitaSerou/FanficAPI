package com.example.fanficapi.service;

import com.example.fanficapi.dto.ChapterDto;
import com.example.fanficapi.dto.simple.SimpleChapterDto;
import com.example.fanficapi.model.Chapter;
import com.example.fanficapi.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChaptersService extends AbstractService<Chapter, Long, SimpleChapterDto, ChapterDto> {

    private final ChapterRepository chapterRepository;

    @Override
    public void saveToDB(Chapter object) {

    }

    @Override
    public Chapter update(Chapter object) {
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
    public List<ChapterDto> getAllDto() {
        return null;
    }

    @Override
    public SimpleChapterDto getSimpleDtoById(Long id) {
        return null;
    }

    @Override
    public ChapterDto getDtoById(Long id) {
        return null;
    }

    @Override
    public Chapter findByName(String name) {
        return null;
    }
}
