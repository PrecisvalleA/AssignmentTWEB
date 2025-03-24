package com.AssignmentTWEB.springboot.repository;

import com.AssignmentTWEB.springboot.model.Genre;
import com.AssignmentTWEB.springboot.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    List<Genre> findByMovie(Movie movie); //find all movie's genres
}
