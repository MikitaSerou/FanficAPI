package com.example.fanficapi.service.impl;

import com.example.fanficapi.config.TestConfig;
import com.example.fanficapi.exception.PublicationException;
import com.example.fanficapi.model.Publication;
import com.example.fanficapi.model.Theme;
import com.example.fanficapi.repository.PublicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(classes = TestConfig.class)
class PublicationServiceImplTest {

    @Mock
    private PublicationRepository publicationRepository;
    @InjectMocks
    private PublicationServiceImpl publicationService;

    private final Theme theme1 = Theme.builder().id(1).name("theme1").build();
    private final Theme theme2 = Theme.builder().id(1).name("theme2").build();

    private final Publication mockPublication1 = Publication
            .builder()
            .id(1L)
            .name("publication")
            .description("description")
            .theme(theme1)
            .build();

    private final Publication mockPublication2 = Publication
            .builder()
            .id(2L)
            .name("publication2")
            .theme(theme2)
            .description("description2")
            .build();

    private final Publication mockPublication3 = Publication
            .builder()
            .id(3L)
            .name("publication3")
            .theme(theme2)
            .description("description3")
            .build();

    private final Publication mockPublication4 = Publication
            .builder()
            .id(4L)
            .name("publication4")
            .theme(theme1)
            .description("description4")
            .build();

    List<Publication> publications = List.of(mockPublication1, mockPublication2, mockPublication3, mockPublication4);

    private final Publication mockPublication = mock(Publication.class);

    @DisplayName("Find publication by id")
    @Test
    void findByIdTest() {
        when(publicationRepository.findById(1L)).thenReturn(Optional.ofNullable(mockPublication1));
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
        when(publicationRepository.findByName("publication")).thenReturn(Optional.ofNullable(mockPublication1));
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
        when(publicationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(publications));
        Pageable pageable = PageRequest.of(0, 1000000000);
        Page<Publication> pageResult = publicationService.findAll(pageable);
        List<Publication> entityResult = pageResult.getContent();
        assertThat(entityResult, equalToObject(publications));
        assertThat(entityResult.get(0).getId(), is(1L));
        assertThat(entityResult.get(3).getId(), is(4L));
        verify(publicationRepository).findAll(pageable);
    }

    @DisplayName("Find all publications with descending order")
    @Test
    void findAllWithSortingAndHalfOfResultPageTest() {
        List<Publication> publicationsDescById = publications.stream()
                .sorted(Comparator.comparing(Publication::getId).reversed()).collect(Collectors.toList());

        when(publicationRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(publicationsDescById));
        Pageable pageable = PageRequest.of(0, 1000000000, Sort.by("id").descending()); //in fact - this is have not sense, bcs we have mock
        Page<Publication> pageResult = publicationService.findAll(pageable);
        List<Publication> entityResult = pageResult.getContent();
        assertThat(entityResult, equalToObject(publicationsDescById));
        assertThat(entityResult, hasSize(4));
        assertThat(entityResult.get(0).getId(), is(4L));
        assertThat(entityResult.get(3).getId(), is(1L));
        verify(publicationRepository).findAll(pageable);
    }

    @DisplayName("Find publications by themeId")
    @Test
    void findAllByThemeId() {
        when(publicationRepository.findAllByThemeId(eq(1), any())).thenReturn(new PageImpl<>(List.of(mockPublication1, mockPublication4)));
        when(publicationRepository.findAllByThemeId(eq(2), any())).thenReturn(new PageImpl<>(List.of(mockPublication2, mockPublication3)));
        Pageable pageable = PageRequest.of(0, 1000000000);
        Page<Publication> pageResultByTheme1 = publicationService.findAllByThemeId(1, pageable);
        Page<Publication> pageResultByTheme2 = publicationService.findAllByThemeId(2, pageable);
        List<Publication> entityResult1 = pageResultByTheme1.getContent();
        List<Publication> entityResult2 = pageResultByTheme2.getContent();
        assertThat(entityResult1, equalToObject(List.of(mockPublication1, mockPublication4)));
        assertThat(entityResult1.get(0).getTheme().getId(), is(1));
        assertThat(entityResult1.get(1).getTheme().getId(), is(1));

        assertThat(entityResult2, equalToObject(List.of(mockPublication2, mockPublication3)));
        assertThat(entityResult2.get(0).getTheme().getId(), is(1));
        assertThat(entityResult2.get(1).getTheme().getId(), is(1));

        verify(publicationRepository).findAllByThemeId(1, pageable);
        verify(publicationRepository).findAllByThemeId(2, pageable);
    }
}
