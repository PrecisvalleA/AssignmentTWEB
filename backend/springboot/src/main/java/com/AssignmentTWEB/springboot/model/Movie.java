package com.AssignmentTWEB.springboot.model; // package class

import jakarta.persistence.*; //JPA annotations

@Entity // this class is a JPA entity
@Table(name = "movies") //table name in the database
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment ID
    private Integer id_movie;

    @Column(nullable = false) //Not Null
    private String name;

    @Column(columnDefinition = "TEXT") //TEXT type
    private String description;

    @Column(columnDefinition = "TEXT") //TEXT type
    private String tagline;

    private double minute;

    private double rating;

    public Movie(){}

    public Movie(Integer id_movie, String name, String description, String tagline, Double minute, Double rating) {
        this.id_movie = id_movie;
        this.name = name;
        this.description = description;
        this.tagline = tagline;
        this.minute = minute;
        this.rating = rating;
    }

    public int getId_movie(){return id_movie;}
    public void setId_movie(Integer id_movie){this.id_movie = id_movie;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    public String getTagline(){return tagline;}
    public void setTagline(String tagline){this.tagline = tagline;}

    public double getMinute(){return minute;}
    public void setMinute(Double minute){this.minute = minute;}

    public double getRating(){return rating;}
    public void setRating(Double rating){this.rating = rating;}
}
