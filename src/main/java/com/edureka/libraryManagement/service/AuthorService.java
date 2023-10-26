package com.edureka.libraryManagement.service;

import com.edureka.libraryManagement.entity.Author;
import com.edureka.libraryManagement.entity.Book;
import com.edureka.libraryManagement.repository.AuthorRepository;
import com.edureka.libraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public ResponseEntity<?> getAllAuthors() {
        List<Author> allAuthors = authorRepository.findAll();
        if (!allAuthors.isEmpty()) {
            return ResponseEntity.ok(allAuthors);
        }
        return new ResponseEntity<>("No Authors Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> addNewAuthor(Author author) {
        try {
            authorRepository.save(author);
            return new ResponseEntity<>("Author Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new author" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAuthorById(Long id) {
        Optional<Author> authorDetails = authorRepository.findById(id);
        if(authorDetails.isPresent())
        {
            return ResponseEntity.ok(authorDetails.get());
        }
        return new ResponseEntity<>("No author with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateAuthor(Author author, Long id) {
        Optional<Author> authorDetails = authorRepository.findById(id);
        if(authorDetails.isPresent()){
            try {
                authorRepository.save(author);
                return new ResponseEntity<>("Author Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating author" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No author with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
        return new ResponseEntity<>("Author Details deleted successfully", HttpStatus.OK);
    }
}
