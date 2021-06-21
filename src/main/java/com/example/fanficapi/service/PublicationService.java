package com.example.fanficapi.service;


import com.example.fanficapi.dto.simple.PreviewPublicationDto;
import com.example.fanficapi.exception.PublicationNotFoundException;
import com.example.fanficapi.mapper.Mapper;
import com.example.fanficapi.model.Publication;
import com.example.fanficapi.model.Tag;
import com.example.fanficapi.model.User;
import com.example.fanficapi.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @Transactional
    public List<Publication> findAll(){
        return publicationRepository.findAll();
    }

    public PreviewPublicationDto getSimpleById(Long id){
        Optional<Publication> publication = publicationRepository.findById(id);
       return mapper.publicationToSimpleDto(publication.get());
//        return publicationRepository.findById(id)
//                .orElseThrow(() ->
//                        new PublicationNotFoundException("Publication with this id (" + id + ") was not found"));
    }

//    public Publication findByName(String name){
//        return publicationRepository.findByName(name)
//                .orElseThrow(
//                        () -> new PublicationNotFoundException("Publication with this name (" + name +") was not found"));
//    }

    public Publication findByName(String name){
       return  publicationRepository.findByName(name)
                .orElseThrow(
                        () -> new PublicationNotFoundException("Publication with this name (" + name +") was not found"));
    }

    public List<Publication> findByAuthorId(Long authorId){
        return publicationRepository.findAllByAuthor_Id(authorId);
    }

    public List<Publication> findAllByThemeId(Integer themeId){
        return publicationRepository.findAllByTheme_Id(themeId);
    }

    public List<Publication> findAllByTagNames(Set<Tag> tags){
        return publicationRepository.findAllByTagsIn(tags);
    }

    public List<Publication> findAllBookMarksByUserId(Long userId){  //TODO  Deal with "IN" queries in SDJ and to above
        Set<User> user = Collections.singleton(userService.findById(userId));
        return publicationRepository.findAllByUsersWhoDidBookmarkIn(user);
    }
}
