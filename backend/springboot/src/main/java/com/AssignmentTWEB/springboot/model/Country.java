package com.AssignmentTWEB.springboot.model;

import com.AssignmentTWEB.springboot.primarykey.CountryPrimaryKey;
import jakarta.persistence.*;

@Entity
@Table(name = "countries")
public class Country {

    @EmbeddedId
    private CountryPrimaryKey id_country;

    @ManyToOne
    @MapsId("id_movie")
    @JoinColumn(name = "id_movie", nullable = false)
    private Movie movie;

    @Column(nullable = false)
    private String country;

    public Country() {}

    public Country(Movie movie, String country) {
        this.id_country= new CountryPrimaryKey(movie.getId_movie(), country);
        this.movie = movie;
        this.country = country;
    }

}
