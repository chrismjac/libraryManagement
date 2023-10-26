package com.edureka.libraryManagement.services;

import com.edureka.libraryManagement.entity.Author;
import com.edureka.libraryManagement.repository.AuthorRepository;
import com.edureka.libraryManagement.service.AuthorService;
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
public class AuthorServiceTests {
    @Mock
    AuthorRepository authorRepositoryMock;
    @InjectMocks
    AuthorService authorService;
    private Author author;

    @BeforeEach
    public void setUp(){
        author = Author.builder()
                .id(1L)
                .name("Test Author")
                .build();
    }

    @Test
    @DisplayName("Test for adding new author")
    public void addNewAuthorTest()
    {
        when(authorRepositoryMock.save(author)).thenReturn(author);
        assertSame(authorService.addNewAuthor(author).getStatusCode(), HttpStatus.OK);
    }
    @Test
    @DisplayName("Test for retrieving all authors")
    public void getAllAuthorsTest()
    {
        when(authorRepositoryMock.findAll()).thenReturn(Collections.singletonList(author));
        assertSame(authorService.getAllAuthors().getStatusCode(), HttpStatus.OK);
        assertEquals(authorService.getAllAuthors().getBody(), Collections.singletonList(author));
    }
    @Test
    @DisplayName("Test for retrieving author by id")
    public void getAuthorByIdTest()
    {
        when(authorRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(author));
        assertSame(authorService.getAuthorById(1L).getStatusCode(), HttpStatus.OK);
        assertSame(authorService.getAuthorById(1L).getBody(), author);
    }
    @Test
    @DisplayName("Test for updating author by id")
    public void updateAuthor()
    {
        when(authorRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(author));
        Author updatedAuthor = author;
        updatedAuthor.setName("Updated authorName");
        when(authorRepositoryMock.save(updatedAuthor)).thenReturn(updatedAuthor);
        assertSame(authorService.updateAuthor(updatedAuthor,1L).getStatusCode(), HttpStatus.OK);
        assertSame(authorService.updateAuthor(updatedAuthor,1L).getBody(), "Author Details updated successfully");
    }
    @Test
    @DisplayName("Test for deleting author by id")
    public void deleteAuthor()
    {
        Mockito.doNothing().when(authorRepositoryMock).deleteById(anyLong());
        assertSame(authorService.deleteAuthorById(1L).getStatusCode(), HttpStatus.OK);
        assertSame(authorService.deleteAuthorById(1L).getBody(), "Author Details deleted successfully");
    }
}
