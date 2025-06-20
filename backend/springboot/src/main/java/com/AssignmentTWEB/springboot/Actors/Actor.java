package com.AssignmentTWEB.springboot.Actors;

import com.AssignmentTWEB.springboot.Movies.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity class representing an Actor.
 * Maps to the "actors" table in the database.
 * Each actor is linked to a Movie (Many-to-One relationship).
 */
@Entity
@Table(name = "actors")
public class Actor {


    /** Unique identifier for the actor (Primary Key, auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The movie to which this actor belongs (Foreign Key). */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movie", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Movie movie;

    /** The name of the actor. Cannot be null. */
    @Column(nullable = false)
    private String name;

    /** The role played by the actor in the movie. Cannot be null. */
    @Column(nullable = false)
    private String role;

    /** Default constructor. Required by JPA*/
    public Actor() {}

    /**
     * Constructor to initialize all mandatory fields.
     *
     * @param movie the associated movie
     * @param name  the name of the actor
     * @param role  the role played by the actor
     */
    public Actor(Movie movie, String name, String role) {
        this.movie = movie;
        this.name = name;
        this.role = role;
    }

    /** Getters and setters for the Actor parameters */

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