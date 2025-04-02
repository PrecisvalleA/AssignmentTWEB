package com.AssignmentTWEB.springboot.model;

import com.AssignmentTWEB.springboot.primarykey.ReleasePrimaryKey;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "releases")
public class Release {

    @EmbeddedId
    private ReleasePrimaryKey id_release; // primary key

    @ManyToOne // one film has more than one release
    @MapsId("id_movie") // use movie for primary key FK
    @JoinColumn(name = "id_movie", nullable = false) // foreign key link movie
    private Movie movie;

    private String rating;

    public Release() {}

    public Release(Movie movie, String country, String type, String date, String rating) {
        this.id_release = new ReleasePrimaryKey(movie.getId_movie(), date, country, type);
        this.movie = movie;
        this.rating = rating;
    }



    public ReleasePrimaryKey getId_release() { return id_release; }
    public void setId_release(ReleasePrimaryKey id_release) { this.id_release = id_release; }

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
}
