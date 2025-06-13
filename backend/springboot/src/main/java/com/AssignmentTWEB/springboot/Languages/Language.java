package com.AssignmentTWEB.springboot.Languages;

import com.AssignmentTWEB.springboot.Movies.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movie", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Movie movie;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String language;

    public Language() {}

    public Language(Movie movie, String type, String language) {
        this.movie = movie;
        this.type = type;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
            this.language = language;
    }
}
