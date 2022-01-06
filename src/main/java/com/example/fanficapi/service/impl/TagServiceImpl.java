package com.example.fanficapi.service.impl;

import com.example.fanficapi.exception.TagException;
import com.example.fanficapi.model.Tag;
import com.example.fanficapi.repository.TagRepository;
import com.example.fanficapi.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;


    @Override
    public void saveToDB(Tag tag) {

    }

    @Override
    public Tag update(Tag tag) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Tag> findAll() {
        return null;
    }

    @Override
    public Tag findById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() ->
                        new TagException("Teg with this id (" + id + ") was not found"));
    }

    @Override
    public Tag findByUsername(String name) {
        return null;
    }

    @Override
    public Set<Tag> findByThemeId(Integer themeId) {
        return tagRepository.findByThemeId(themeId);
    }
}
