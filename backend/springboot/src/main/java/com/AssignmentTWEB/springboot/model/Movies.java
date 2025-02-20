package com.AssignmentTWEB.springboot.model; // package class

import jakarta.persistence.*; //JPA annotations

@Entity // this class is a JPA entity
@Table(name = "movies") //table name in the database
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment ID
    private Long id_movie;

    @Column(nullable = false) //Not Null
    private String name;

    @Column(columnDefinition = "TEXT") //TEXT type
    private String description;

<<<<<<<
=======
    
>>>>>>> recupero-commit
}