package com.edureka.libraryManagement.service;

import com.edureka.libraryManagement.entity.Borrower;
import com.edureka.libraryManagement.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowerService {
    @Autowired
    BorrowerRepository borrowerRepository;

    public ResponseEntity<?> getAllBorrowers() {
        List<Borrower> allBorrowers = borrowerRepository.findAll();
        if (!allBorrowers.isEmpty()) {
            return ResponseEntity.ok(allBorrowers);
        }
        return new ResponseEntity<>("No Borrowers Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> addNewBorrower(Borrower borrower) {
        try {
            borrowerRepository.save(borrower);
            return new ResponseEntity<>("Borrower Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new borrower" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getBorrowerById(Long id) {
        Optional<Borrower> borrowerDetails = borrowerRepository.findById(id);
        if(borrowerDetails.isPresent())
        {
            return ResponseEntity.ok(borrowerDetails.get());
        }
        return new ResponseEntity<>("No borrower with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateBorrower(Borrower borrower, Long id) {
        ResponseEntity<?> borrowerDetails =getBorrowerById(id);
        if(borrowerDetails.getStatusCode().is2xxSuccessful()){
            try {
                borrowerRepository.save(borrower);
                return new ResponseEntity<>("Borrower Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating borrower" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No borrower with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteBorrowerById(Long id) {
        borrowerRepository.deleteById(id);
        return new ResponseEntity<>("Borrower Details deleted successfully", HttpStatus.OK);
    }
}
