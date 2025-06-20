package com.AssignmentTWEB.springboot.Genres;

import com.AssignmentTWEB.springboot.Movies.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity class representing a Genre associated with a Movie.
 * Maps to the "genres" table in the database.
 */
@Entity
@Table(name = "genres")
public class Genre {

    /** Unique identifier for the genre entry (Primary Key, auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The movie to which this genre belongs (Foreign Key). */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movie", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Movie movie;

    /** The genre name (e.g., Action, Comedy). Cannot be null. */
    @Column(nullable = false)
    private String genre;

    /** Default constructor required by JPA. */
    public Genre() {}

    /**
     * Constructor to initialize Genre with mandatory fields.
     *
     * @param movie the associated movie
     * @param genre the genre name
     */
    public Genre(Movie movie, String genre) {
        this.movie = movie;
        this.genre = genre;
    }

    /** Getters and setters for the Genre parameters */

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
}