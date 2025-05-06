package com.AssignmentTWEB.springboot.Studios;

import com.AssignmentTWEB.springboot.Movies.Movie;
import jakarta.persistence.*;

@Entity
@Table(name = "studios")
public class Studio {

    @EmbeddedId
    private StudioPrimaryKey id_studio; //primary key

    @ManyToOne //one film has more than 1 actor
    @MapsId("id_movie") //use movie for primary key FK
    @JoinColumn(name = "id_movie", nullable = false) //foreign key link movie
    private Movie movie;

    public Studio() {}

    public Studio(Movie movie, String studio) {
        this.id_studio = new StudioPrimaryKey(movie.getId_movie(), studio);
        this.movie = movie;
    }

    public StudioPrimaryKey getId_studio() {return id_studio;}
    public void setId_studio(StudioPrimaryKey id_studio) {this.id_studio = id_studio;}

    public Movie getMovie() {return movie;}
    public void setMovie(Movie movie) {this.movie = movie;}

}
