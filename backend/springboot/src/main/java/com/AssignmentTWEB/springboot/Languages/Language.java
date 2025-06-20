package com.AssignmentTWEB.springboot.Languages;

import com.AssignmentTWEB.springboot.Movies.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity class representing a Language associated with a Movie.
 * Maps to the "languages" table in the database.
 */
@Entity
@Table(name = "languages")
public class Language {

    /** Unique identifier for the language entry (Primary Key, auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The movie to which this language entry belongs (Foreign Key). */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movie", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Movie movie;

    /** The type of language. Cannot be null. */
    @Column(nullable = false)
    private String type;

    /** The name of the language. Cannot be null. */
    @Column(nullable = false)
    private String language;

    /** Default constructor required by JPA. */
    public Language() {}

    /**
     * Constructor to initialize Language with mandatory fields.
     *
     * @param movie associated movie
     * @param type  type of language (spoken, subtitle, etc.)
     * @param language language name
     */
    public Language(Movie movie, String type, String language) {
        this.movie = movie;
        this.type = type;
        this.language = language;
    }

    /** Getters and setters for the language parameters */

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
            this.language = language;
    }
}
