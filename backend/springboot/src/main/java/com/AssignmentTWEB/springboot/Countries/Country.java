package com.AssignmentTWEB.springboot.Countries;

import com.AssignmentTWEB.springboot.Movies.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity class representing a Country associated with a Movie.
 * Maps to the "countries" table in the database.
 * Each country entry is linked to a specific movie (Many-to-One relationship).
 */
@Entity
@Table(name = "countries")
public class Country {

    /** Unique identifier for the country entry (Primary Key, auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The movie to which this country entry belongs (Foreign Key). */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movie", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Movie movie;

    /** The name of the country. Cannot be null. */
    @Column(nullable = false)
    private String country;

    /** Default constructor. Required by JPA. */
    public Country() {}


    /**
     * Constructor to initialize all mandatory fields.
     *
     * @param movie   the associated movie
     * @param country the name of the country
     */
    public Country(Movie movie, String country) {
        this.movie = movie;
        this.country = country;
    }

    /** Getters and setters for the Country parameters */

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
