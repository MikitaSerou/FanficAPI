package com.example.fanficapi.repository;

import com.example.fanficapi.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

    Optional<Publication> findByName(String name);

    Page<Publication> findAllByThemeId(Integer themeId, Pageable pageable);
}
