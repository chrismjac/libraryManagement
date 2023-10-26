package com.edureka.libraryManagement.repository;

import com.edureka.libraryManagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByGenreId(Long id);

    List<Book> findAllByAuthorsId(Long id);

    List<Book> findAllByLibraryBranchId(Long id);

    List<Book> findAllByBorrowerId(Long id);
}
