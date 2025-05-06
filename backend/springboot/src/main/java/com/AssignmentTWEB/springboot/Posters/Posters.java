package com.AssignmentTWEB.springboot.Posters;

import com.AssignmentTWEB.springboot.Movies.Movie;
import jakarta.persistence.*;

@Entity
@Table(name = "posters")
public class Posters {

    @EmbeddedId
    private PostersPrimaryKey id_posters;

    @OneToOne
    @MapsId("id_movie")
    @JoinColumn(name = "id_movie", nullable = false)
    private Movie movie;

    public Posters() {}

    public Posters(Movie movie, String link) {
        this.id_posters= new PostersPrimaryKey(movie.getId_movie(), link);
        this.movie = movie;
    }

    public PostersPrimaryKey getId_posters() {return id_posters;}
    public void setId_posters(PostersPrimaryKey id_posters) {this.id_posters = id_posters;}

    public Movie getMovie() {return movie;}
    public void setMovie(Movie movie) {this.movie = movie;}

}
