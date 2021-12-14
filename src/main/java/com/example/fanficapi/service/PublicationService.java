package com.example.fanficapi.service;


import com.example.fanficapi.dto.PublicationDto;
import com.example.fanficapi.dto.simple.PreviewPublicationDto;
import com.example.fanficapi.exception.PublicationException;
import com.example.fanficapi.model.Publication;
import com.example.fanficapi.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationService extends AbstractService<Publication, Long, PreviewPublicationDto, PublicationDto> {

    private final PublicationRepository publicationRepository;


    @Override
    public List<Publication> findAll() {
        return publicationRepository.findAll();
    }

    @Override
    public List<PublicationDto> getAllDto() {
        List<PublicationDto> publicationsDto = new ArrayList<>();
        return super.mapper.publicationsListToDto(findAll());
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
    public void saveToDB(Publication object) {

    }


    @Override
    public Publication update(Publication object) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public PreviewPublicationDto getSimpleDtoById(Long id) {
        return mapper.publicationToPreviewDto(findById(id));
    }

    @Override
    public PublicationDto getDtoById(Long id) {
        return mapper.publicationToDto(findById(id));
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