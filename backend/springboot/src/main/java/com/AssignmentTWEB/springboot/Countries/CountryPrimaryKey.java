package com.AssignmentTWEB.springboot.Countries;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CountryPrimaryKey implements Serializable {
    private Integer id_movie;
    private String country;

    public CountryPrimaryKey() {}

    public CountryPrimaryKey(Integer id_movie, String country) {
        this.id_movie = id_movie;
        this.country = country;
    }

    public Integer getMovie() {
        return id_movie;
    }
    public void setMovie(Integer id_movie) {
        this.id_movie = id_movie;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryPrimaryKey countryPrimaryKey = (CountryPrimaryKey) o;
        return Objects.equals(id_movie, countryPrimaryKey.id_movie)
                && Objects.equals(country, countryPrimaryKey.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, country);
    }
}
