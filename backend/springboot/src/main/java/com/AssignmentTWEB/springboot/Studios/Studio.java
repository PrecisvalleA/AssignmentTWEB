package com.AssignmentTWEB.springboot.Studios;

import com.AssignmentTWEB.springboot.Movies.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity class representing a Studio associated with a Movie.
 * Maps to the "studios" table in the database.
 */
@Entity
@Table(name = "studios")
public class Studio {

    /** Unique identifier for the studio entry (Primary Key, auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The movie to which this studio belongs (Foreign Key). */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movie", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Movie movie;

    /** The name of the studio. */
    @Column(nullable = false)
    private String studio;

    /** Default constructor required by JPA. */
    public Studio() {}

    /**
     * Constructor to initialize Studio with mandatory fields.
     *
     * @param movie  the associated movie
     * @param studio the studio name
     */
    public Studio(Movie movie, String studio) {
        this.movie = movie;
        this.studio = studio;
    }

    /** Getters and setters for the Studio parameters */

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getStudio() {
        return studio;
    }
    public void setStudio(String studio) {
        this.studio = studio;
    }
}
