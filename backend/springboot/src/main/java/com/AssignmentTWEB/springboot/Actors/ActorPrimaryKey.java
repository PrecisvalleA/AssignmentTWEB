package com.AssignmentTWEB.springboot.Actors;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ActorPrimaryKey implements Serializable {
    private Integer id_movie;
    private String name;
    private String role;

    public ActorPrimaryKey() {}

    public ActorPrimaryKey(Integer id_movie, String name, String role) {
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorPrimaryKey actorPrimaryKey = (ActorPrimaryKey) o;
        return Objects.equals(id_movie, actorPrimaryKey.id_movie)
                && Objects.equals(name, actorPrimaryKey.name)
                && Objects.equals(role, actorPrimaryKey.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, name, role);
    }
}
