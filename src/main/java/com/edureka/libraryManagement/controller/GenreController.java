package com.edureka.libraryManagement.controller;

import com.edureka.libraryManagement.entity.Genre;
import com.edureka.libraryManagement.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    @Autowired
    GenreService genreService;

    @PostMapping
    @Operation(summary = "Add a new genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genre added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add genre")})
    private ResponseEntity<String> addNewGenre(@RequestBody Genre genre){
        return genreService.addNewGenre(genre);
    }

    @GetMapping
    @Operation(summary = "Retrieve all genres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genres retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Genres Added")})
    private ResponseEntity<?> getAllGenres(){
        return genreService.getAllGenres();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a genre by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified genre details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No genre with specified Id found")})
    private ResponseEntity<?> getGenreById(@PathVariable Long id){
        return genreService.getGenreById(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified genre details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified genre details updating failed"),
            @ApiResponse(responseCode = "404", description = "No genre with specified Id found")})
    private ResponseEntity<String> updateGenre(@RequestBody Genre genre,@PathVariable Long id){
        return genreService.updateGenre(genre,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified genre details updated successfully")})
    private ResponseEntity<String> deleteGenreById(@PathVariable Long id){
        return genreService.deleteGenreById(id);
    }
}
