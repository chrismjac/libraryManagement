package com.edureka.libraryManagement.controller;

import com.edureka.libraryManagement.entity.Borrower;
import com.edureka.libraryManagement.service.BorrowerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    @Autowired
    BorrowerService borrowerService;

    @PostMapping
    @Operation(summary = "Add a new Borrower")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Borrower added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Borrower")})
    private ResponseEntity<String> addNewBorrower(@RequestBody Borrower borrower){
        return borrowerService.addNewBorrower(borrower);
    }

    @GetMapping
    @Operation(summary = "Retrieve all Borrowers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Borrowers retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Borrowers Added")})
    private ResponseEntity<?> getAllBorrowers(){
        return borrowerService.getAllBorrowers();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a Borrower by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Borrower details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Borrower with specified Id found")})
    private ResponseEntity<?> getBorrowerById(@PathVariable Long id){
        return borrowerService.getBorrowerById(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a Borrower")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Borrower details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Borrower details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Borrower with specified Id found")})
    private ResponseEntity<String> updateBorrower(@RequestBody Borrower borrower,@PathVariable Long id){
        return borrowerService.updateBorrower(borrower,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Borrower")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Borrower details updated successfully")})
    private ResponseEntity<String> deleteBorrowerById(@PathVariable Long id){
        return borrowerService.deleteBorrowerById(id);
    }
}
