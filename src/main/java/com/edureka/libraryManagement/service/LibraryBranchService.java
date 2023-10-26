package com.edureka.libraryManagement.service;

import com.edureka.libraryManagement.entity.LibraryBranch;
import com.edureka.libraryManagement.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryBranchService {
    @Autowired
    LibraryRepository libraryRepository;

    public ResponseEntity<?> getAllBranches() {
        List<LibraryBranch> allBranches = libraryRepository.findAll();
        if (!allBranches.isEmpty()) {
            return ResponseEntity.ok(allBranches);
        }
        return new ResponseEntity<>("No Branches Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> addNewLibraryBranch(LibraryBranch libraryBranch) {
        try {
            libraryRepository.save(libraryBranch);
            return new ResponseEntity<>("Branch Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new branch" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getBranchById(Long id) {
        Optional<LibraryBranch> branchDetails = libraryRepository.findById(id);
        if(branchDetails.isPresent())
        {
            return ResponseEntity.ok(branchDetails.get());
        }
        return new ResponseEntity<>("No branch with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateLibraryBranch(LibraryBranch libraryBranch, Long id) {
        ResponseEntity<?> branchDetails =getBranchById(id);
        if(branchDetails.getStatusCode().is2xxSuccessful()){
            try {
                libraryRepository.save(libraryBranch);
                return new ResponseEntity<>("Branch Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating branch" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No branch with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteBranchById(Long id) {
        libraryRepository.deleteById(id);
        return new ResponseEntity<>("Branch Details deleted successfully", HttpStatus.OK);
    }
}
