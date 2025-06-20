package com.AssignmentTWEB.springboot.Studios;


import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Studio entity.
 * Provides CRUD operations and custom queries for Studios.
 */
@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

    /**
     * Retrieve all studios associated with a specific movie.
     *
     * @param movie the movie entity
     * @return list of studios for the given movie
     */
    List<Studio> findByMovie(Movie movie); //find all movie's studios

}
