package com.AssignmentTWEB.springboot.Genres;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Genre entity.
 * Provides CRUD operations and custom queries for Genres.
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT g FROM Genre g WHERE g.movie.id IN :ids")
    List<Genre> findGenresByMovieIds(@Param("ids") List<Integer> ids);
}
