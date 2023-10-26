package com.edureka.libraryManagement.services;

import com.edureka.libraryManagement.entity.LibraryBranch;
import com.edureka.libraryManagement.repository.LibraryRepository;
import com.edureka.libraryManagement.service.LibraryBranchService;
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
public class LibraryBranchServiceTests {
    @Mock
    LibraryRepository libraryRepositoryMock;
    @InjectMocks
    LibraryBranchService libraryBranchService;
    private LibraryBranch libraryBranch;

    @BeforeEach
    public void setUp(){
        libraryBranch = LibraryBranch.builder()
                .id(1L)
                .branchName("Test LibraryBranch")
                .address("Test Branch Address")
                .contactNumber(0L)
                .build();
    }

    @Test
    @DisplayName("Test for adding new libraryBranch")
    public void addNewLibraryBranchTest()
    {
        when(libraryRepositoryMock.save(libraryBranch)).thenReturn(libraryBranch);
        assertSame(libraryBranchService.addNewLibraryBranch(libraryBranch).getStatusCode(), HttpStatus.OK);
    }
    @Test
    @DisplayName("Test for retrieving all library Branches")
    public void getAllLibraryBranchesTest()
    {
        when(libraryRepositoryMock.findAll()).thenReturn(Collections.singletonList(libraryBranch));
        assertSame(libraryBranchService.getAllBranches().getStatusCode(), HttpStatus.OK);
        assertEquals(libraryBranchService.getAllBranches().getBody(), Collections.singletonList(libraryBranch));
    }
    @Test
    @DisplayName("Test for retrieving libraryBranch by id")
    public void getLibraryBranchByIdTest()
    {
        when(libraryRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(libraryBranch));
        assertSame(libraryBranchService.getBranchById(1L).getStatusCode(), HttpStatus.OK);
        assertSame(libraryBranchService.getBranchById(1L).getBody(), libraryBranch);
    }
    @Test
    @DisplayName("Test for updating libraryBranch by id")
    public void updateLibraryBranch()
    {
        when(libraryRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(libraryBranch));
        LibraryBranch updatedLibraryBranch = libraryBranch;
        updatedLibraryBranch.setBranchName("Updated libraryBranchName");
        when(libraryRepositoryMock.save(updatedLibraryBranch)).thenReturn(updatedLibraryBranch);
        assertSame(libraryBranchService.updateLibraryBranch(updatedLibraryBranch,1L).getStatusCode(), HttpStatus.OK);
        assertSame(libraryBranchService.updateLibraryBranch(updatedLibraryBranch,1L).getBody(), "Branch Details updated successfully");
    }
    @Test
    @DisplayName("Test for deleting libraryBranch by id")
    public void deleteLibraryBranch()
    {
        Mockito.doNothing().when(libraryRepositoryMock).deleteById(anyLong());
        assertSame(libraryBranchService.deleteBranchById(1L).getStatusCode(), HttpStatus.OK);
        assertSame(libraryBranchService.deleteBranchById(1L).getBody(), "Branch Details deleted successfully");
    }
}
