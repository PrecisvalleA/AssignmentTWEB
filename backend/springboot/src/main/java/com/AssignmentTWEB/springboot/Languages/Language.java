package com.AssignmentTWEB.springboot.Languages;

import com.AssignmentTWEB.springboot.Movies.Movie;
import jakarta.persistence.*;

@Entity
@Table(name = "languages")
public class Language {

    @EmbeddedId
    private LanguagePrimaryKey id_language; //primary key

    @ManyToOne
    @MapsId("id_movie") //use movie for primary key FK
    @JoinColumn(name = "id_movie", nullable = false) //foreign key link movie
    private Movie movie;

    public Language() {}

    public Language(Movie movie, String type, String language) {
        this.id_language = new LanguagePrimaryKey(movie.getId_movie(), type, language);
        this.movie = movie;
    }

    public LanguagePrimaryKey getId_language() {return id_language;}
    public void setId_language(LanguagePrimaryKey id_language) {this.id_language = id_language;}

    public Movie getMovie() {return movie;}
    public void setMovie(Movie movie) {this.movie = movie;}

}
