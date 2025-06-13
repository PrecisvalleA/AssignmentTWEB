package com.AssignmentTWEB.springboot.Movies; // package class

import jakarta.persistence.*; //JPA annotations

@Entity// this class is a JPA entity
@Table(name = "movies") //table name in the database

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment ID
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false) //Not Null
    private String name;

    @Column(columnDefinition = "TEXT", precision = 4) //TEXT type
    private Integer date;

    @Column(columnDefinition = "TEXT") //TEXT type
    private String description;

    @Column(columnDefinition = "TEXT") //TEXT type
    private String tagline;

    private Double minute;

    private Double rating;

    public Movie(){}

    public Movie(Integer id, String name, Integer date, String description,
                 String tagline, Double minute, Double rating) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.tagline = tagline;
        this.minute = minute;
        this.rating = rating;
    }

    public Integer getId_movie(){return id;}
    public void setId_movie(Integer id){this.id = id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public Integer getDate(){return date;}
    public void setDate(Integer date){this.date = date;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    public String getTagline(){return tagline;}
    public void setTagline(String tagline){this.tagline = tagline;}

    public Double getMinute(){return minute;}
    public void setMinute(Double minute){this.minute = minute;}

    public Double getRating(){return rating;}
    public void setRating(Double rating){this.rating = rating;}
}
