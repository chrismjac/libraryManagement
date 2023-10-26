package com.edureka.libraryManagement.services;

import com.edureka.libraryManagement.entity.Book;
import com.edureka.libraryManagement.repository.BookRepository;
import com.edureka.libraryManagement.service.BookService;
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
public class BookServiceTests {
    @Mock
    BookRepository bookRepositoryMock;
    @InjectMocks
    BookService bookService;
    private Book book;

    @BeforeEach
    public void setUp(){
        book = Book.builder()
                .id(1L)
                .title("Test Book")
                .description("Test description")
                .publishedYear(2005)
                .build();
    }

    @Test
    @DisplayName("Test for adding new book")
    public void addNewBookTest()
    {
        when(bookRepositoryMock.save(book)).thenReturn(book);
        assertSame(bookService.addNewBook(book).getStatusCode(), HttpStatus.OK);
    }
    @Test
    @DisplayName("Test for retrieving all books")
    public void getAllBooksTest()
    {
        when(bookRepositoryMock.findAll()).thenReturn(Collections.singletonList(book));
        assertSame(bookService.getAllBooks().getStatusCode(), HttpStatus.OK);
        assertEquals(bookService.getAllBooks().getBody(), Collections.singletonList(book));
    }
    @Test
    @DisplayName("Test for retrieving book by id")
    public void getBookByIdTest()
    {
        when(bookRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(book));
        assertSame(bookService.getBookById(1L).getStatusCode(), HttpStatus.OK);
        assertSame(bookService.getBookById(1L).getBody(), book);
    }
    @Test
    @DisplayName("Test for updating book by id")
    public void updateBook()
    {
        when(bookRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(book));
        Book updatedBook = book;
        updatedBook.setTitle("Updated bookName");
        when(bookRepositoryMock.save(updatedBook)).thenReturn(updatedBook);
        assertSame(bookService.updateBook(updatedBook,1L).getStatusCode(), HttpStatus.OK);
        assertSame(bookService.updateBook(updatedBook,1L).getBody(), "Book Details updated successfully");
    }
    @Test
    @DisplayName("Test for deleting book by id")
    public void deleteBook()
    {
        Mockito.doNothing().when(bookRepositoryMock).deleteById(anyLong());
        assertSame(bookService.deleteBookById(1L).getStatusCode(), HttpStatus.OK);
        assertSame(bookService.deleteBookById(1L).getBody(), "Book Details deleted successfully");
    }
}
