package com.AssignmentTWEB.springboot.Countries;

import com.AssignmentTWEB.springboot.Movies.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movie", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Movie movie;

    @Column(nullable = false)
    private String country;

    public Country() {}

    public Country(Movie movie, String country) {
        this.movie = movie;
        this.country = country;
    }

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
