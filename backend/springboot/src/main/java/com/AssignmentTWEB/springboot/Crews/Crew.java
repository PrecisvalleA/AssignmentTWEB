package com.AssignmentTWEB.springboot.Crews;

import com.AssignmentTWEB.springboot.Movies.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity class representing a Crew member involved in a Movie production.
 * Maps to the "crews" table in the database.
 */
@Entity
@Table(name = "crews")
public class Crew {

    /** Unique identifier for the crew member (Primary Key, auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The movie to which this crew member belongs (Foreign Key). */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movie", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Movie movie;

    /** The role of the crew member (e.g., Director, Producer). Cannot be null. */
    @Column(nullable = false)
    private String role;

    /** The name of the crew member. Cannot be null. */
    @Column(nullable = false)
    private String name;

    /** Default constructor required by JPA. */
    public Crew() {}

    /**
     * Constructor to initialize Crew with mandatory fields.
     *
     * @param movie associated movie
     * @param name name of the crew member
     * @param role role performed in the production
     */
    public Crew(Movie movie, String name, String role) {
        this.movie = movie;
        this.role = role;
        this.name = name;
    }

    /** Getters and setters for the Crew parameters */

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}