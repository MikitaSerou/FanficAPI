package com.example.fanficapi.service;

import com.example.fanficapi.dto.TagDto;
import com.example.fanficapi.dto.simple.SimpleTagDto;
import com.example.fanficapi.exception.TagNotFoundException;
import com.example.fanficapi.model.Tag;
import com.example.fanficapi.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService extends AbstractService<Tag, Long, SimpleTagDto, TagDto> {

    @Autowired
    private TagRepository tagRepository;


    @Override
    public void saveToDB(Tag object) {

    }

    @Override
    public Tag update(Tag object) {
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
                        new TagNotFoundException("Teg with this id (" + id + ") was not found"));
    }

    @Override
    public List<TagDto> getAllDto() {
        return null;
    }

    @Override
    public SimpleTagDto getSimpleDtoById(Long id) {
        return null;
    }

    @Override
    public TagDto getDtoById(Long id) {
        return null;
    }

    @Override
    public Tag findByName(String name) {
        return null;
    }
}
