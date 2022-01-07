package com.example.fanficapi.service.impl;


import com.example.fanficapi.exception.PublicationException;
import com.example.fanficapi.model.Publication;
import com.example.fanficapi.repository.PublicationRepository;
import com.example.fanficapi.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;


    @Override
    public List<Publication> findAll() {
        return publicationRepository.findAll();
    }


    @Override
    public Publication findById(Long id) {
        return publicationRepository.findById(id)
                .orElseThrow(
                        () -> new PublicationException("Publication with this id (" + id + ") was not found"));
    }

    @Override
    public Publication findByUsername(String name) {
        return publicationRepository.findByName(name)
                .orElseThrow(
                        () -> new PublicationException("Publication with this name (" + name + ") was not found"));
    }

    @Override
    public void saveToDB(Publication publication) {

    }

    @Override
    public Publication update(Publication publication) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

//    public List<Publication> findByAuthorId(Long authorId) {
//        return publicationRepository.findAllByAuthor_Id(authorId);
//    }
//
//    public List<Publication> findAllByThemeId(Integer themeId) {
//        return publicationRepository.findAllByTheme_Id(themeId);
//    }
//
//    public List<Publication> findAllByTagNames(Set<Tag> tags) {
//        return publicationRepository.findAllByTagsIn(tags);
//    }

//    public List<Publication> findAllBookMarksByUserId(Long userId){  //TODO  Deal with "IN" queries in SDJ and to above
//        Set<User> user = Collections.singleton(userService.findById(userId));
//        return publicationRepository.findAllByUsersWhoDidBookmarkIn(user);
//    }
