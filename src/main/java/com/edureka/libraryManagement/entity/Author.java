package com.edureka.libraryManagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "author")
@Data
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="author_book",
            joinColumns = @JoinColumn(name = "book_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id",referencedColumnName = "id"))
    private List<Book> books;
}
