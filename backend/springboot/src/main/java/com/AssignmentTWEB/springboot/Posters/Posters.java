package com.AssignmentTWEB.springboot.Posters;

import com.AssignmentTWEB.springboot.Movies.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity class representing a Poster associated with a Movie.
 * Maps to the "posters" table in the database.
 */
@Entity
@Table(name = "posters")
public class Posters {
    /** Unique identifier for the poster entry (Primary Key, auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The movie to which this poster belongs (Foreign Key, one-to-one relationship). */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movie", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Movie movie;

    /** The URL link to the poster image. */
    @Column(nullable = false, length = 512)
    private String link;

    /** Default constructor required by JPA. */
    public Posters() {}

    /**
     * Constructor to initialize Posters with mandatory fields.
     *
     * @param movie the associated movie
     * @param link  the poster image URL
     */
    public Posters(Movie movie, String link) {
        this.movie = movie;
        this.link = link;
    }

    /** Getters and setters for the Poster parameters */

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

}
