package com.AssignmentTWEB.springboot.primarykey;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GenrePrimaryKey implements Serializable {
    private Integer id_movie;
    private String genre;

    public GenrePrimaryKey() {}

    public GenrePrimaryKey(Integer id_movie, String genre) {
        this.id_movie = id_movie;
        this.genre = genre;
    }

    public Integer getMovie() {
        return id_movie;
    }
    public void setMovie(Integer id_movie) {
        this.id_movie = id_movie;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.AssignmentTWEB.springboot.primarykey.GenrePrimaryKey genrePrimaryKey = (com.AssignmentTWEB.springboot.primarykey.GenrePrimaryKey) o;
        return Objects.equals(id_movie, genrePrimaryKey.id_movie)
                && Objects.equals(genre, genrePrimaryKey.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, genre);
    }
}