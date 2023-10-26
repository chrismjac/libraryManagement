package com.edureka.libraryManagement.services;

import com.edureka.libraryManagement.entity.Genre;
import com.edureka.libraryManagement.repository.GenreRepository;
import com.edureka.libraryManagement.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GenreServiceTests {
    @Mock
    GenreRepository genreRepositoryMock;
    @InjectMocks
    GenreService genreService;
    private Genre genre;

    @BeforeEach
    public void setUp(){
        genre = Genre.builder()
                .id(1L)
                .genreName("Test Genre")
                .build();
    }

    @Test
    @DisplayName("Test for adding new genre")
    public void addNewGenreTest()
    {
        when(genreRepositoryMock.save(genre)).thenReturn(genre);
        assertSame(genreService.addNewGenre(genre).getStatusCode(), HttpStatus.OK);
    }
    @Test
    @DisplayName("Test for retrieving all genres")
    public void getAllGenresTest()
    {
        when(genreRepositoryMock.findAll()).thenReturn(Collections.singletonList(genre));
        assertSame(genreService.getAllGenres().getStatusCode(), HttpStatus.OK);
        assertEquals(genreService.getAllGenres().getBody(), Collections.singletonList(genre));
    }
    @Test
    @DisplayName("Test for retrieving genre by id")
    public void getGenreByIdTest()
    {
        when(genreRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(genre));
        assertSame(genreService.getGenreById(1L).getStatusCode(), HttpStatus.OK);
        assertSame(genreService.getGenreById(1L).getBody(), genre);
    }
    @Test
    @DisplayName("Test for updating genre by id")
    public void updateGenre()
    {
        when(genreRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(genre));
        Genre updatedGenre = genre;
        updatedGenre.setGenreName("Updated genreName");
        when(genreRepositoryMock.save(updatedGenre)).thenReturn(updatedGenre);
        assertSame(genreService.updateGenre(updatedGenre,1L).getStatusCode(), HttpStatus.OK);
        assertSame(genreService.updateGenre(updatedGenre,1L).getBody(), "Genre Details updated successfully");
    }
    @Test
    @DisplayName("Test for deleting genre by id")
    public void deleteGenre()
    {
        Mockito.doNothing().when(genreRepositoryMock).deleteById(anyLong());
        assertSame(genreService.deleteGenreById(1L).getStatusCode(), HttpStatus.OK);
        assertSame(genreService.deleteGenreById(1L).getBody(), "Genre Details deleted successfully");
    }
}
