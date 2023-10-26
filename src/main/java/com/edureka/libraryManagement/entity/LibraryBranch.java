package com.edureka.libraryManagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "library_branch")
@Data
@Builder
public class LibraryBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branchName;
    private String address;
    private Long contactNumber;
    @OneToMany
    private List<Book> books;
}
