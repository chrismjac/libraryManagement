package com.edureka.libraryManagement.repository;

import com.edureka.libraryManagement.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

}
