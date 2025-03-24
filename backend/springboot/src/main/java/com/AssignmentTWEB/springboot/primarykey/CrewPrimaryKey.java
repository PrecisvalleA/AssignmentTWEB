package com.AssignmentTWEB.springboot.primarykey;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CrewPrimaryKey implements Serializable {
    private Integer id_movie;
    private String role;
    private String name;

    public CrewPrimaryKey() {}

    public CrewPrimaryKey(Integer id_movie, String role, String name) {
        this.id_movie = id_movie;
        this.name = name;
        this.role = role;
    }

    public Integer getMovie() {
        return id_movie;
    }
    public void setMovie(Integer id_movie) {
        this.id_movie = id_movie;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.AssignmentTWEB.springboot.primarykey.CrewPrimaryKey crewPrimaryKey = (com.AssignmentTWEB.springboot.primarykey.CrewPrimaryKey) o;
        return Objects.equals(id_movie, crewPrimaryKey.id_movie)
                && Objects.equals(role, crewPrimaryKey.role)
                && Objects.equals(name, crewPrimaryKey.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, role, name);
    }
}