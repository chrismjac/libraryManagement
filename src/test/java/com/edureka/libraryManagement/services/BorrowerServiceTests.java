package com.edureka.libraryManagement.services;

import com.edureka.libraryManagement.entity.Borrower;
import com.edureka.libraryManagement.repository.BorrowerRepository;
import com.edureka.libraryManagement.service.BorrowerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BorrowerServiceTests {
    @Mock
    BorrowerRepository borrowerRepositoryMock;
    @InjectMocks
    BorrowerService borrowerService;
    private Borrower borrower;

    @BeforeEach
    public void setUp(){
        Date date = new Date();
        borrower = Borrower.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Borrower")
                .activeSince(date)
                .dateOfBirth(date)
                .borrowedBookOn(date)
                .lateFeeAmount(0.0F)
                .membershipStatus("Active")
                .contactNumber(0L)
                .build();
    }

    @Test
    @DisplayName("Test for adding new borrower")
    public void addNewBorrowerTest()
    {
        when(borrowerRepositoryMock.save(borrower)).thenReturn(borrower);
        assertSame(borrowerService.addNewBorrower(borrower).getStatusCode(), HttpStatus.OK);
    }
    @Test
    @DisplayName("Test for retrieving all borrowers")
    public void getAllBorrowersTest()
    {
        when(borrowerRepositoryMock.findAll()).thenReturn(Collections.singletonList(borrower));
        assertSame(borrowerService.getAllBorrowers().getStatusCode(), HttpStatus.OK);
        assertEquals(borrowerService.getAllBorrowers().getBody(), Collections.singletonList(borrower));
    }
    @Test
    @DisplayName("Test for retrieving borrower by id")
    public void getBorrowerByIdTest()
    {
        when(borrowerRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(borrower));
        assertSame(borrowerService.getBorrowerById(1L).getStatusCode(), HttpStatus.OK);
        assertSame(borrowerService.getBorrowerById(1L).getBody(), borrower);
    }
    @Test
    @DisplayName("Test for updating borrower by id")
    public void updateBorrower()
    {
        when(borrowerRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(borrower));
        Borrower updatedBorrower = borrower;
        updatedBorrower.setLastName("Updated borrowerName");
        when(borrowerRepositoryMock.save(updatedBorrower)).thenReturn(updatedBorrower);
        assertSame(borrowerService.updateBorrower(updatedBorrower,1L).getStatusCode(), HttpStatus.OK);
        assertSame(borrowerService.updateBorrower(updatedBorrower,1L).getBody(), "Borrower Details updated successfully");
    }
    @Test
    @DisplayName("Test for deleting borrower by id")
    public void deleteBorrower()
    {
        Mockito.doNothing().when(borrowerRepositoryMock).deleteById(anyLong());
        assertSame(borrowerService.deleteBorrowerById(1L).getStatusCode(), HttpStatus.OK);
        assertSame(borrowerService.deleteBorrowerById(1L).getBody(), "Borrower Details deleted successfully");
    }
}
