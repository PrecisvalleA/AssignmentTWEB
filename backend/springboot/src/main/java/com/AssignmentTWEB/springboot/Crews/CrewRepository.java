package com.AssignmentTWEB.springboot.Crews;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Crew entity.
 * Provides CRUD operations and custom queries for Crew members.
 */
@Repository
public interface CrewRepository extends JpaRepository<Crew, Long> {

    /**
     * Retrieve all crew members associated with a specific movie.
     *
     * @param movie the movie entity
     * @return list of crew members for the given movie
     */
    List<Crew> findByMovie(Movie movie); //find all movie's crew
}
