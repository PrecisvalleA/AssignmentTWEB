package com.AssignmentTWEB.springboot.Releases;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Release entity.
 * Provides CRUD operations and custom queries for Releases.
 */
@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {

    /**
     * Retrieve all releases associated with a specific movie.
     *
     * @param movie the movie entity
     * @return list of releases for the given movie
     */
    List<Release> findByMovie(Movie movie); // Find all releases by movie ID
}
