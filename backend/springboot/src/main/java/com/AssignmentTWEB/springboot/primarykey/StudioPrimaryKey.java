package com.AssignmentTWEB.springboot.primarykey;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class StudioPrimaryKey implements Serializable{
    private Integer id_movie;
    private String studio;


    public StudioPrimaryKey() {}

    public StudioPrimaryKey(Integer id_movie, String studio) {
        this.id_movie = id_movie;
        this.studio = studio;
    }

    public Integer getMovie() {
        return id_movie;
    }
    public void setMovie(Integer id_movie) {
        this.id_movie = id_movie;
    }
    public String getStudio() {
        return studio;
    }
    public void setStudio(String studio) {
        this.studio = studio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudioPrimaryKey studioPrimaryKey = (StudioPrimaryKey) o;
        return Objects.equals(id_movie, studioPrimaryKey.id_movie)
                && Objects.equals(studio, studioPrimaryKey.studio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, studio);
    }
}

