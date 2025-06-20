package com.AssignmentTWEB.springboot.Themes;

import com.AssignmentTWEB.springboot.Movies.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity class representing a Theme associated with a Movie.
 * Maps to the "themes" table in the database.
 */
@Entity
@Table(name = "themes")
public class Theme {

    /** Unique identifier for the theme entry (Primary Key, auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The movie to which this theme belongs (Foreign Key). */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movie", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Movie movie;


    /** The theme of the movie. */
    @Column(nullable = false)
    private String theme;

    /** Default constructor required by JPA. */
    public Theme() {}

    /**
     * Constructor to initialize Theme with mandatory fields.
     *
     * @param movie  the associated movie
     * @param theme  the theme name
     */
    public Theme(Movie movie, String theme) {
        this.movie = movie;
        this.theme = theme;
    }

    /** Getters and setters for the Theme parameters */

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
}