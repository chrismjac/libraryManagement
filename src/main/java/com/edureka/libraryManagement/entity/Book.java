package com.edureka.libraryManagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "book")
@Data
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    private Genre genre;
    @ManyToMany(mappedBy = "books")
    private List<Author>  authors;
    @ManyToOne
    private LibraryBranch libraryBranch;
    private String availabilityStatus;
    @ManyToOne
    private Borrower borrower;
    private int publishedYear;
}
