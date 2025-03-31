package com.AssignmentTWEB.springboot.primarykey;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDateTime;

@Embeddable
public class ReleasePrimaryKey implements Serializable {
    private Long id_movie;
    private String country;
    private LocalDateTime date;
    private String type;

    public ReleasePrimaryKey(Integer idMovie, String date, String country, String type) {}

    public ReleasePrimaryKey(Long id_movie, LocalDateTime date, String country, String type) {
        this.id_movie = id_movie;
        this.date = date;
        this.country = country;
        this.type = type;
    }

    public Long getId_movie() { return id_movie; }
    public void setId_movie(Long id_movie) { this.id_movie = id_movie; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReleasePrimaryKey that = (ReleasePrimaryKey) o;
        return Objects.equals(id_movie, that.id_movie) &&
                Objects.equals(date, that.date) &&
                Objects.equals(country, that.country) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, date, country, type);
    }
}
