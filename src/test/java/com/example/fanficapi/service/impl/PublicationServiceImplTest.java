package com.example.fanficapi.service.impl;

import com.example.fanficapi.exception.PublicationException;
import com.example.fanficapi.model.Publication;
import com.example.fanficapi.repository.PublicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
class PublicationServiceImplTest {

    @Mock
    private PublicationRepository publicationRepository;
    @InjectMocks
    private PublicationServiceImpl publicationService;

    private final Publication publication = Publication
            .builder()
            .name("publication")
            .description("description")
            .build();

    private final Publication publication2 = Publication
            .builder()
            .name("publication2")
            .description("description2")
            .build();

    List<Publication> publications = List.of(publication, publication2);

    private final Publication mockPublication = mock(Publication.class);

    @DisplayName("Find publication by id")
    @Test
    void findByIdTest() {
        when(publicationRepository.findById(1L)).thenReturn(Optional.ofNullable(publication));
        publicationService.findById(1L);

        verify(publicationRepository).findById(1L);
    }

    @DisplayName("Find not existence publication by id")
    @Test
    void findNotExistenceById() {
        when(publicationRepository.findById(4L)).thenReturn(Optional.empty());
        assertThrows(PublicationException.class, () -> publicationService.findById(4L));

        verify(publicationRepository).findById(4L);
    }

    @DisplayName("Find by name")
    @Test
    void findByNameTest() {
        when(publicationRepository.findByName("publication")).thenReturn(Optional.ofNullable(publication));
        publicationService.findByName("publication");

        verify(publicationRepository).findByName("publication");
    }

    @DisplayName("Find by not existence name")
    @Test
    void findByNotExistenceNameTest() {
        when(publicationRepository.findByName("publication4")).thenReturn(Optional.empty());
        assertThrows(PublicationException.class, () -> publicationService.findByName("publication"));

        verify(publicationRepository).findByName("publication");
    }

    @DisplayName("Find all publications")
    @Test
    void findAllTest() {
        when(publicationRepository.findAll()).thenReturn(publications);
        publicationService.findAll();

        verify(publicationRepository).findAll();
    }

//    @DisplayName("Save publication")
//    @Test
//    void saveTest() {
//        when(publicationRepository.save(publication)).thenReturn(publication);
//        publicationService.save(publication);
//
//        verify(publicationRepository).save(publication);
//    }

//    @DisplayName("Update publication")
//    @Test
//    void updateTest() {
//        when(publicationRepository.save(publication)).thenReturn(publication);
//        publicationService.update(publication);
//
//        verify(publicationRepository).save(publication);
//    }

//    @DisplayName("Delete publication")
//    @Test
//    void deleteTest() {
//        publicationService.delete(publication);
//
//        verify(publicationRepository).delete(publication);
//    }
}
