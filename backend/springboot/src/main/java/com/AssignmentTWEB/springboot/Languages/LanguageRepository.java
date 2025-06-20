package com.AssignmentTWEB.springboot.Languages;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Language entity.
 * Provides CRUD operations and custom queries for Languages.
 */
@Repository
public interface LanguageRepository extends JpaRepository<Language, Long>{

    /**
     * Retrieve all languages associated with a specific movie.
     *
     * @param movie the movie entity
     * @return list of languages related to the given movie
     */
    List<Language> findByMovie(Movie movie);

}
