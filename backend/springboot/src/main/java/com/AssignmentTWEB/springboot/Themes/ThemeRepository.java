
package com.AssignmentTWEB.springboot.Themes;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Theme entity.
 * Provides CRUD operations and custom queries for Themes.
 */
@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    /**
     * Retrieve all themes associated with a specific movie.
     *
     * @param movie the movie entity
     * @return list of themes for the given movie
     */
    List<Theme> findByMovie(Movie movie); //find all movie's themes
}
