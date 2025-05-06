package com.AssignmentTWEB.springboot.Languages;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LanguagePrimaryKey implements Serializable {
    private Integer id_movie;
    private String type;
    private String language;

    public LanguagePrimaryKey() {}

    public LanguagePrimaryKey(Integer id_movie, String type, String language) {
        this.id_movie = id_movie;
        this.type = type;
        this.language = language;
    }

    public Integer getMovie() {
        return id_movie;
    }
    public void setMovie(Integer id_movie) {
        this.id_movie = id_movie;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguagePrimaryKey languagePrimaryKey = (LanguagePrimaryKey) o;
        return Objects.equals(id_movie, languagePrimaryKey.id_movie)
                && Objects.equals(type, languagePrimaryKey.type)
                && Objects.equals(language, languagePrimaryKey.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, type, language);
    }
}
