package com.edureka.libraryManagement.repository;

import com.edureka.libraryManagement.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

}
