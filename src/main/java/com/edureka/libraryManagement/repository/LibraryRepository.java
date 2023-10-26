package com.edureka.libraryManagement.repository;

import com.edureka.libraryManagement.entity.LibraryBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryBranch,Long> {

}
