package com.AssignmentTWEB.springboot.Posters;

import com.AssignmentTWEB.springboot.Movies.Movie;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Posters entity.
 * Provides CRUD operations and custom queries for Posters.
 */
@Repository
public interface PostersRepository extends JpaRepository <Posters, Long>{

    /**
     * Retrieve the poster associated with a specific movie.
     *
     * @param movie the movie entity
     * @return the poster entity associated with the movie
     */
    Posters findByMovie(Movie movie);
}
