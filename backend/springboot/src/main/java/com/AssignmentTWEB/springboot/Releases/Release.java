package com.AssignmentTWEB.springboot.Releases;

import com.AssignmentTWEB.springboot.Movies.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity class representing a Release of a Movie.
 * Maps to the "releases" table in the database.
 */
@Entity
@Table(name = "releases")
public class Release {
    /** Unique identifier for the release entry (Primary Key, auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The movie to which this release belongs (Foreign Key). */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movie", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Movie movie;

    /** The release date (as string). Cannot be null. */
    @Column(nullable = false)
    private String date;

    /** The country where the release occurred. Cannot be null. */
    @Column(nullable = false)
    private String country;

    /** The type of release. Cannot be null. */
    @Column(nullable = false)
    private String type;

    /** Default constructor required by JPA. */
    public Release() {}

    /**
     * Constructor to initialize Release with mandatory fields.
     *
     * @param movie   the associated movie
     * @param date    the release date
     * @param country the release country
     * @param type    the release type
     */
    public Release(Movie movie, String date, String country, String type) {
        this.movie = movie;
        this.date = date;
        this.country = country;
        this.type = type;
    }

    /** Getters and setters for the Release parameters */

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
