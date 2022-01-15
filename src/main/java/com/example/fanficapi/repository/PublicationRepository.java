package com.example.fanficapi.repository;

import com.example.fanficapi.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

    List<Publication> findAllByAuthor_Id(Long authorId);

    List<Publication> findAllByTheme_Id(Integer themeId);

    //List<Publication> findAllByTagsIn(Set<Tag> tags);

    Optional<Publication> findByName(String name);

    // List<Publication> findAllByUsersWhoDidBookmarkIn(Set<User> user); //TODO write query
}
