package com.example.fanficapi.service;

import com.example.fanficapi.exception.TagNotFoundException;
import com.example.fanficapi.model.Tag;
import com.example.fanficapi.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;


    public Tag findById(Long id){
        return tagRepository.findById(id)
                .orElseThrow(() ->
                        new TagNotFoundException("Teg with this id (" + id + ") was not found"));
    }
}
