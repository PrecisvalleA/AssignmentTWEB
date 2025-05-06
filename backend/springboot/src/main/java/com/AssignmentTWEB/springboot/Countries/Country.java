package com.AssignmentTWEB.springboot.Countries;

import com.AssignmentTWEB.springboot.Movies.Movie;
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

    public Country() {}

    public Country(Movie movie, String country) {
        this.id_country= new CountryPrimaryKey(movie.getId_movie(), country);
        this.movie = movie;
    }

    public CountryPrimaryKey getId_country() {return id_country;}
    public void setId_country(CountryPrimaryKey id_country) {this.id_country = id_country;}

    public Movie getMovie() {return movie;}
    public void setMovie(Movie movie) {this.movie = movie;}

}
