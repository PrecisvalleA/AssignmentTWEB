package com.AssignmentTWEB.springboot.Movies; // package class

import jakarta.persistence.*; //JPA annotations


/**
 * Entity class representing a Movie.
 * Maps to the "movies" table in the database.
 */
@Entity// this class is a JPA entity
@Table(name = "movies") //table name in the database

public class Movie {
    /** Unique identifier for the movie (Primary Key, auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment ID
    @Column(name = "id")
    private Integer id;

    /** The name (title) of the movie. Cannot be null. */
    @Column(nullable = false) //Not Null
    private String name;

    /** The release year of the movie (stored as Integer). */
    @Column(columnDefinition = "TEXT", precision = 4) //TEXT type
    private Integer date;

    /** The plot of the movie. */
    @Column(columnDefinition = "TEXT") //TEXT type
    private String description;

    /** The slogan of the movie. */
    @Column(columnDefinition = "TEXT") //TEXT type
    private String tagline;

    /** The duration in minute of the movie */
    private Double minute;

    /** The rating of the movie */
    private Double rating;

    /** Default constructor. */
    public Movie(){}


    /**
     * Parameterized constructor to create a Movie object.
     *
     * @param id unique identifier
     * @param name movie title
     * @param date release year
     * @param description plot summary
     * @param tagline slogan
     * @param minute duration in minutes
     * @param rating rating value
     */
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

    //Getters and setters
    public Integer getId(){return id;}
    public void setId(Integer id){this.id = id;}

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
