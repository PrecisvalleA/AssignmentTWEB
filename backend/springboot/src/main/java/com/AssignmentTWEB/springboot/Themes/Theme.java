package com.AssignmentTWEB.springboot.Themes;

import com.AssignmentTWEB.springboot.Movies.Movie;
import jakarta.persistence.*;

@Entity
@Table(name = "themes")
public class Theme {

    @EmbeddedId
    private ThemePrimaryKey id_theme; //primary key

    @ManyToOne //one film has more than 1 theme
    @MapsId("id_movie") //use movie for primary key FK
    @JoinColumn(name = "id_movie", nullable = false) //foreign key link movie
    private Movie movie;

    public Theme() {}

    public Theme(Movie movie, String theme) {
        this.id_theme = new ThemePrimaryKey(movie.getId_movie(), theme);
        this.movie = movie;
    }

    public ThemePrimaryKey getId_Theme() {return id_theme;}
    public void setId_theme(ThemePrimaryKey id_theme) {this.id_theme = id_theme;}

    public Movie getMovie() {return movie;}
    public void setMovie(Movie movie) {this.movie = movie;}

}