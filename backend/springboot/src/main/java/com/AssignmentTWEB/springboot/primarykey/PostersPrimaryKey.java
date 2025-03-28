package com.AssignmentTWEB.springboot.primarykey;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostersPrimaryKey implements Serializable {
    private Integer id_movie;
    private String posters;

    public PostersPrimaryKey() {}

    public PostersPrimaryKey(Integer id_movie, String posters) {
        this.id_movie = id_movie;
        this.posters = posters;
    }

    public Integer getMovie() {
        return id_movie;
    }
    public void setMovie(Integer id_movie) {
        this.id_movie = id_movie;
    }
    public String getPosters() {
        return posters;
    }
    public void setPosters(String posters) {
        this.posters = posters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostersPrimaryKey postersPrimaryKey = (PostersPrimaryKey) o;
        return Objects.equals(id_movie, postersPrimaryKey.id_movie)
                && Objects.equals(posters, postersPrimaryKey.posters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, posters);
    }
}
