package com.AssignmentTWEB.springboot.Genres;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Genre entity.
 * Provides CRUD operations and custom queries for Genres.
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    /**
     * Retrieve all genres associated with a specific movie.
     *
     * @param movie the movie entity
     * @return list of genres related to the given movie
     */
    List<Genre> findByMovie(Movie movie); //find all movie's genres
}
