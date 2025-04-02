package com.AssignmentTWEB.springboot.primarykey;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDateTime;


@Embeddable
public class ReleasePrimaryKey implements Serializable {
    private Integer id_movie;
    private String country;
    private String date;
    private String type;

    public ReleasePrimaryKey(Integer id_movie, String date, String country, String type) {
        this.id_movie = id_movie;
        this.date = date;
        this.country = country;
        this.type = type;
    }

    public ReleasePrimaryKey() {

    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public Integer getId_movie() { return id_movie; }
    public void setId_movie(Integer id_movie) { this.id_movie = id_movie; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReleasePrimaryKey releasePrimaryKey = (ReleasePrimaryKey) o;
        return Objects.equals(id_movie, releasePrimaryKey.id_movie)
                && Objects.equals(date, releasePrimaryKey.date)
                && Objects.equals(country, releasePrimaryKey.country)
                && Objects.equals(type, releasePrimaryKey.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, date, country, type);
    }
}
