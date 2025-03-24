package com.AssignmentTWEB.springboot.model;

import com.AssignmentTWEB.springboot.primarykey.GenrePrimaryKey;
import jakarta.persistence.*;

@Entity
@Table(name = "genre")
public class Genre {

    @EmbeddedId
    private GenrePrimaryKey id_genre; //primary key

    @ManyToOne //one film has more than 1 genre
    @MapsId("id_movie") //use movie for primary key FK
    @JoinColumn(name = "id_movie", nullable = false) //foreign key link movie
    private Movie movie;

    public Genre() {}

    public Genre(Movie movie, String genre) {
        this.id_genre = new GenrePrimaryKey(movie.getId_movie(), genre);
        this.movie = movie;
    }

    public GenrePrimaryKey getId_genre() {return id_genre;}
    public void setId_genre(GenrePrimaryKey id_genre) {this.id_genre = id_genre;}

    public Movie getMovie() {return movie;}
    public void setMovie(Movie movie) {this.movie = movie;}

}