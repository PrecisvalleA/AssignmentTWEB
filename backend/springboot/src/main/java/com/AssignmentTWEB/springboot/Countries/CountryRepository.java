package com.AssignmentTWEB.springboot.Countries;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Country entity.
 * Provides CRUD operations and custom queries for Countries.
 */
@Repository
public interface CountryRepository extends JpaRepository <Country, Long>{

    /**
     * Retrieve all countries associated with a specific movie.
     *
     * @param movie the movie entity
     * @return list of countries related to the given movie
     */
    List<Country> findByMovie(Movie movie);
}
