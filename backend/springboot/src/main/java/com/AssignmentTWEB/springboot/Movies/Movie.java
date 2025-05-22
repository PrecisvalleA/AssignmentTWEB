package com.AssignmentTWEB.springboot.Movies; // package class

import com.AssignmentTWEB.springboot.Actors.Actor;
import com.AssignmentTWEB.springboot.Crews.Crew;
import com.AssignmentTWEB.springboot.Countries.Country;
import com.AssignmentTWEB.springboot.Genres.Genre;
import com.AssignmentTWEB.springboot.Posters.Posters;
import com.AssignmentTWEB.springboot.Releases.Release;
import com.AssignmentTWEB.springboot.Studios.Studio;
import com.AssignmentTWEB.springboot.Themes.Theme;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*; //JPA annotations

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity // this class is a JPA entity
@Table(name = "movies") //table name in the database
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment ID
    @Column(name = "id_movie")
    private Integer id_movie;

    @Column(nullable = false) //Not Null
    private String name;

    @Column(columnDefinition = "TEXT") //TEXT type
    private String date;

    @Column(columnDefinition = "TEXT") //TEXT type
    private String description;

    @Column(columnDefinition = "TEXT") //TEXT type
    private String tagline;

    private Double minute;

    private Double rating;

    public Movie(){}

    public Movie(Integer id_movie, String name, String date, String description, String tagline, Double minute, Double rating) {
        this.id_movie = id_movie;
        this.name = name;
        this.date = date;
        this.description = description;
        this.tagline = tagline;
        this.minute = minute;
        this.rating = rating;
    }

    //FetchType.LAZY doesn't load actors for every findbyId()
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Actor> actors = new ArrayList<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Crew> crews = new ArrayList<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Country> countries = new ArrayList<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Release> releases = new ArrayList<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Studio> studios = new ArrayList<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Theme> themes = new ArrayList<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Posters> posters = new ArrayList<>();

    public Integer getId_movie(){return id_movie;}
    public void setId_movie(Integer id_movie){this.id_movie = id_movie;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    public String getTagline(){return tagline;}
    public void setTagline(String tagline){this.tagline = tagline;}

    public Double getMinute(){return minute;}
    public void setMinute(Double minute){this.minute = minute;}

    public Double getRating(){return rating;}
    public void setRating(Double rating){this.rating = rating;}


    //getter list for all tables
    public List<Actor> getActors() {
        return actors;
    }
    public List<Crew> getCrews() {
        return crews;
    }
    public List<Country> getCountries() {
        return countries;
    }
    public List<Genre> getGenres() {
        return genres;
    }
    public List<Release> getReleases() {
        return releases;
    }
    public List<Studio> getStudios() {
        return studios;
    }
    public List<Theme> getThemes() {
        return themes;
    }
    public List<Posters> getPosters() {
        return posters;
    }


}
