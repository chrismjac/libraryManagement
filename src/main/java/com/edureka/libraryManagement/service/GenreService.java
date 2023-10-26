package com.edureka.libraryManagement.service;

import com.edureka.libraryManagement.entity.Genre;
import com.edureka.libraryManagement.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    public ResponseEntity<?> getAllGenres() {
        List<Genre> allGenres = genreRepository.findAll();
        if (!allGenres.isEmpty()) {
            return ResponseEntity.ok(allGenres);
        }
        return new ResponseEntity<>("No Genres Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> addNewGenre(Genre genre) {
        try {
            genreRepository.save(genre);
            return new ResponseEntity<>("Genre Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new genre" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getGenreById(Long id) {
        Optional<Genre> genreDetails = genreRepository.findById(id);
        if(genreDetails.isPresent())
        {
            return ResponseEntity.ok(genreDetails.get());
        }
        return new ResponseEntity<>("No genre with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateGenre(Genre genre, Long id) {
        ResponseEntity<?> genreDetails =getGenreById(id);
        if(genreDetails.getStatusCode().is2xxSuccessful()){
            try {
                genreRepository.save(genre);
                return new ResponseEntity<>("Genre Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating genre" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No genre with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteGenreById(Long id) {
        genreRepository.deleteById(id);
        return new ResponseEntity<>("Genre Details deleted successfully", HttpStatus.OK);
    }
}
