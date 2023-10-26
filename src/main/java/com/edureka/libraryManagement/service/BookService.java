package com.edureka.libraryManagement.service;

import com.edureka.libraryManagement.entity.Book;
import com.edureka.libraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public ResponseEntity<?> getAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        if (!allBooks.isEmpty()) {
            return ResponseEntity.ok(allBooks);
        }
        return new ResponseEntity<>("No Books Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> addNewBook(Book book) {
        try {
            bookRepository.save(book);
            return new ResponseEntity<>("Book Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new book" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getBookById(Long id) {
        Optional<Book> bookDetails = bookRepository.findById(id);
        if (bookDetails.isPresent()) {
            return ResponseEntity.ok(bookDetails.get());
        }
        return new ResponseEntity<>("No book with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateBook(Book book, Long id) {
        ResponseEntity<?> bookDetails = getBookById(id);
        if (bookDetails.getStatusCode().is2xxSuccessful()) {
            try {
                bookRepository.save(book);
                return new ResponseEntity<>("Book Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating book" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No book with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteBookById(Long id) {
        bookRepository.deleteById(id);
        return new ResponseEntity<>("Book Details deleted successfully", HttpStatus.OK);
    }

    public ResponseEntity<?> getBookByGenreId(Long id) {
        List<Book> bookDetails = bookRepository.findAllByGenreId(id);
        if (!bookDetails.isEmpty()) {
            return ResponseEntity.ok(bookDetails);
        }
        return new ResponseEntity<>("No book available for specified genre Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getBookByAuthorId(Long id) {
        List<Book> bookDetails = bookRepository.findAllByAuthorsId(id);
        if (!bookDetails.isEmpty()) {
            return ResponseEntity.ok(bookDetails);
        }
        return new ResponseEntity<>("No book available for specified author Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getBookByLibraryBranchId(Long id) {
        List<Book> bookDetails = bookRepository.findAllByLibraryBranchId(id);
        if (!bookDetails.isEmpty()) {
            return ResponseEntity.ok(bookDetails);
        }
        return new ResponseEntity<>("No book available for specified library branch Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getBookByLibraryBorrowerId(Long id) {
        List<Book> bookDetails = bookRepository.findAllByBorrowerId(id);
        if (!bookDetails.isEmpty()) {
            return ResponseEntity.ok(bookDetails);
        }
        return new ResponseEntity<>("No book available for specified borrower Id found", HttpStatus.NOT_FOUND);
    }
}
