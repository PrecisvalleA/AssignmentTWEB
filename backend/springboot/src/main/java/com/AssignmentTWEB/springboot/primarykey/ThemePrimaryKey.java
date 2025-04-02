package com.AssignmentTWEB.springboot.primarykey;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ThemePrimaryKey implements Serializable {
    private Integer id_movie;
    private String theme;

    public ThemePrimaryKey() {}

    public ThemePrimaryKey(Integer id_movie, String theme) {
        this.id_movie = id_movie;
        this.theme = theme;
    }

    public Integer getMovie() {
        return id_movie;
    }
    public void setMovie(Integer id_movie) {
        this.id_movie = id_movie;
    }
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.AssignmentTWEB.springboot.primarykey.ThemePrimaryKey themePrimaryKey = (com.AssignmentTWEB.springboot.primarykey.ThemePrimaryKey) o;
        return Objects.equals(id_movie, themePrimaryKey.id_movie)
                && Objects.equals(theme, themePrimaryKey.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, theme);
    }
}