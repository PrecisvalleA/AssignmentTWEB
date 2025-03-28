package com.AssignmentTWEB.springboot.primarykey;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostersPrimaryKey implements Serializable {
    private Integer id_movie;

    @Column(length = 512)
    private String link;

    public PostersPrimaryKey() {}

    public PostersPrimaryKey(Integer id_movie, String link) {
        this.id_movie = id_movie;
        this.link = link;
    }

    public Integer getMovie() {
        return id_movie;
    }
    public void setMovie(Integer id_movie) {
        this.id_movie = id_movie;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostersPrimaryKey postersPrimaryKey = (PostersPrimaryKey) o;
        return Objects.equals(id_movie, postersPrimaryKey.id_movie)
                && Objects.equals(link, postersPrimaryKey.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, link);
    }
}
