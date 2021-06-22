package com.example.fanficapi.controller;

import com.example.fanficapi.model.Tag;
import com.example.fanficapi.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
@Slf4j
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping("/find/{id}")
    public ResponseEntity<Tag> getUserById(@PathVariable("id") Long id) {
        Tag tag = tagService.findById(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }
}
