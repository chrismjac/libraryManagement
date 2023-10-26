package com.edureka.libraryManagement.controller;

import com.edureka.libraryManagement.entity.Book;
import com.edureka.libraryManagement.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping
    @Operation(summary = "Add a new Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Book")})
    private ResponseEntity<String> addNewBook(@RequestBody Book book){
        return bookService.addNewBook(book);
    }

    @GetMapping
    @Operation(summary = "Retrieve all Books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Books Added")})
    private ResponseEntity<?> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a Book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Book details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Book with specified Id found")})
    private ResponseEntity<?> getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Book details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Book details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Book with specified Id found")})
    private ResponseEntity<String> updateBook(@RequestBody Book book,@PathVariable Long id){
        return bookService.updateBook(book,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Book details updated successfully")})
    private ResponseEntity<String> deleteBookById(@PathVariable Long id){
        return bookService.deleteBookById(id);
    }
    @GetMapping("/genre/{id}")
    @Operation(summary = "Get a Book by Genre ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Book details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Book with specified genre Id found")})
    private ResponseEntity<?> getBookByGenreId(@PathVariable Long id){
        return bookService.getBookByGenreId(id);
    }
    @GetMapping("/author/{id}")
    @Operation(summary = "Get a Book by Author ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Book details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Book with specified author Id found")})
    private ResponseEntity<?> getBookByAuthorId(@PathVariable Long id){
        return bookService.getBookByAuthorId(id);
    }
    @GetMapping("/library-branch/{id}")
    @Operation(summary = "Get a Book by Library Branch ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Book details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Book with specified library branch Id found")})
    private ResponseEntity<?> getBookByLibraryBranchId(@PathVariable Long id){
        return bookService.getBookByLibraryBranchId(id);
    }
    @GetMapping("/borrower/{id}")
    @Operation(summary = "Get a Book by Borrower ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Book details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Book with specified borrower Id found")})
    private ResponseEntity<?> getBookByBorrowerId(@PathVariable Long id){
        return bookService.getBookByLibraryBorrowerId(id);
    }

}
