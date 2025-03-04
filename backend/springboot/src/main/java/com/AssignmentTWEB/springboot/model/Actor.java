package com.AssignmentTWEB.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
    private Integer id_actor;

    @ManyToOne //one film has more than 1 actor
    @JoinColumn(name = "id_movie", nullable = false) //foreign key link movie
    private Movie movie;

    @Column(nullable = false)
    private String name;

    @Column
    private String role;

    public Actor() {}

    public Actor(Integer id_actor, Movie movie, String name, String role) {
        this.id_actor = id_actor;
        this.movie = movie;
        this.name = name;
        this.role = role;
    }

    public Integer getId_actor() {return id_actor;}
    public void setId_actor(Integer id_actor) {this.id_actor = id_actor;}

    public Movie getMovie() {return movie;}
    public void setMovie(Movie movie) {this.movie = movie;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}

}
