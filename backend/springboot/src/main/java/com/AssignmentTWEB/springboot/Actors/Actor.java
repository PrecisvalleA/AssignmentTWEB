package com.AssignmentTWEB.springboot.Actors;

import com.AssignmentTWEB.springboot.Movies.Movie;
import jakarta.persistence.*;

@Entity
@Table(name = "actors")
public class Actor {

    @EmbeddedId
       private ActorPrimaryKey id_actor; //primary key

    @ManyToOne //one film has more than 1 actor
    @MapsId("id_movie") //use movie for primary key FK
    @JoinColumn(name = "id_movie", nullable = false) //foreign key link movie
    private Movie movie;

    public Actor() {}

    public Actor(Movie movie, String name, String role) {
        this.id_actor = new ActorPrimaryKey(movie.getId_movie(), name, role);
        this.movie = movie;
    }

    public ActorPrimaryKey getId_actor() {return id_actor;}
    public void setId_actor(ActorPrimaryKey id_actor) {this.id_actor = id_actor;}

    public Movie getMovie() {return movie;}
    public void setMovie(Movie movie) {this.movie = movie;}

}
