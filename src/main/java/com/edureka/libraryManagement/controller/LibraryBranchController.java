package com.edureka.libraryManagement.controller;

import com.edureka.libraryManagement.entity.LibraryBranch;
import com.edureka.libraryManagement.service.LibraryBranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library-branches")
public class LibraryBranchController {
    @Autowired
    LibraryBranchService libraryBranchService;

    @PostMapping
    @Operation(summary = "Add a new library branch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Branch added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add branch")})
    private ResponseEntity<String> addNewLibraryBranch(@RequestBody LibraryBranch libraryBranch){
        return libraryBranchService.addNewLibraryBranch(libraryBranch);
    }

    @GetMapping
    @Operation(summary = "Retrieve all library branches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Branches retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Branches Added")})
    private ResponseEntity<?> getAllBranches(){
        return libraryBranchService.getAllBranches();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a library branch by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified branch details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No branch with specified Id found")})
    private ResponseEntity<?> getBranchById(@PathVariable Long id){
        return libraryBranchService.getBranchById(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a library branch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified branch details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified branch details updating failed"),
            @ApiResponse(responseCode = "404", description = "No branch with specified Id found")})
    private ResponseEntity<String> updateLibraryBranch(@RequestBody LibraryBranch libraryBranch,@PathVariable Long id){
        return libraryBranchService.updateLibraryBranch(libraryBranch,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a library branch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified branch details updated successfully")})
    private ResponseEntity<String> deleteBranchById(@PathVariable Long id){
        return libraryBranchService.deleteBranchById(id);
    }
}
