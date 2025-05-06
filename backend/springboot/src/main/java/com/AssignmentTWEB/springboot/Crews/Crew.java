package com.AssignmentTWEB.springboot.Crews;

import com.AssignmentTWEB.springboot.Movies.Movie;
import jakarta.persistence.*;

@Entity
@Table(name = "crew")
public class Crew {

    @EmbeddedId
    private CrewPrimaryKey id_crew; //primary key

    @ManyToOne //one film has more than 1 actor
    @MapsId("id_movie") //use movie for primary key FK
    @JoinColumn(name = "id_movie", nullable = false) //foreign key link movie
    private Movie movie;

    public Crew() {}

    public Crew(Movie movie, String role, String name) {
        this.id_crew = new CrewPrimaryKey(movie.getId_movie(), role, name);
        this.movie = movie;
    }

    public CrewPrimaryKey getId_crew() {return id_crew;}
    public void setId_crew(CrewPrimaryKey id_crew) {this.id_crew = id_crew;}

    public Movie getMovie() {return movie;}
    public void setMovie(Movie movie) {this.movie = movie;}

}