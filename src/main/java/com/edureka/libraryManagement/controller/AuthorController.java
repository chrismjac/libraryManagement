package com.edureka.libraryManagement.controller;

import com.edureka.libraryManagement.entity.Author;
import com.edureka.libraryManagement.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping
    @Operation(summary = "Add a new Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Author")})
    private ResponseEntity<String> addNewAuthor(@RequestBody Author author){
        return authorService.addNewAuthor(author);
    }

    @GetMapping
    @Operation(summary = "Retrieve all Authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authors retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Authors Added")})
    private ResponseEntity<?> getAllAuthors(){
        return authorService.getAllAuthors();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a Author by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Author details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Author with specified Id found")})
    private ResponseEntity<?> getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Author details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Author details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Author with specified Id found")})
    private ResponseEntity<String> updateAuthor(@RequestBody Author author,@PathVariable Long id){
        return authorService.updateAuthor(author,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Author details updated successfully")})
    private ResponseEntity<String> deleteAuthorById(@PathVariable Long id){
        return authorService.deleteAuthorById(id);
    }
}
