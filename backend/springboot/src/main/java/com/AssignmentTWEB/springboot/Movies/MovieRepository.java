package com.AssignmentTWEB.springboot.Movies; // package class

import com.AssignmentTWEB.springboot.Actors.Actor;
import com.AssignmentTWEB.springboot.Countries.Country;
import com.AssignmentTWEB.springboot.Crews.Crew;
import com.AssignmentTWEB.springboot.Genres.Genre;
import com.AssignmentTWEB.springboot.Languages.Language;
import com.AssignmentTWEB.springboot.Posters.Posters;
import com.AssignmentTWEB.springboot.Releases.Release;
import com.AssignmentTWEB.springboot.Studios.Studio;
import com.AssignmentTWEB.springboot.Themes.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List; // list is necessary for function which return many results
import java.util.Optional;
import java.util.Set;

@Repository

public interface MovieRepository extends JpaRepository<Movie, Integer> {//extends JpaRepository for Movie entity with Integer ID

    @Query("SELECT m AS movie, COALESCE(p.link, '') AS posterURL, STRING_AGG(g.genre, ', ') AS genres " +
            "FROM Movie m " +
            "LEFT JOIN Posters p ON p.movie.id = m.id " +
            "LEFT JOIN Genre g ON g.movie.id = m.id " +
            "WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "GROUP BY m.id, p.link")
    Page<MoviePoster> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    @Query("""
        SELECT m AS movie, p.link AS posterUrl, STRING_AGG(g.genre, ', ') AS genres
        FROM Movie m
        LEFT JOIN Posters p ON p.movie.id = m.id
        LEFT JOIN Genre g ON g.movie.id = m.id
        WHERE m.rating IS NOT NULL
           AND (:minRating IS NULL OR m.rating >= :minRating)
           AND (:maxRating IS NULL OR m.rating <= :maxRating)
           AND (:minDate IS NULL OR m.date >= :minDate)
           AND (:maxDate IS NULL OR m.date <= :maxDate)
           AND (:minDur IS NULL OR m.minute >= :minDur)
           AND (:maxDur IS NULL OR m.minute <= :maxDur)
           AND (:genre IS NULL OR g.genre ILIKE :genre)
           GROUP BY m.id, p.link
           """)
        Page<MoviePoster> findWithAllFilters(
                @Param("minRating") Double minRating,
                @Param("maxRating") Double maxRating,
                @Param("minDate")   String minDate,
                @Param("maxDate")   String maxDate,
                @Param("minDur")    Double minDur,
                @Param("maxDur")    Double maxDur,
                @Param("genre")     String genre,
                Pageable pageable
        );

    @Query("SELECT m FROM Movie m WHERE m.id = :id")
    Movie findMovieById(@Param("id") Integer id);


    @Query("SELECT DISTINCT m, p.link AS posterUrl \n" +
            "FROM Movie m \n" +
            "LEFT JOIN Posters p \n" +
            "ON m.id = p.movie.id \n" +
            "WHERE m.rating IS NOT NULL \n")
    Page<Movie> findMoviesWithPosters(Pageable pageable);

    @Query("SELECT a FROM Actor a WHERE a.movie.id = :id ")
    Set<Actor> findActorsByMovieId(@Param("id") Integer id);

    @Query("SELECT c FROM Crew c WHERE c.movie.id = :id")
    Set<Crew> findCrewsByMovieId(@Param("id") Integer id);

    @Query("SELECT co FROM Country co WHERE co.movie.id = :id")
    Set<Country> findCountriesByMovieId(@Param("id") Integer id);

    @Query("SELECT g FROM Genre g WHERE g.movie.id = :id")
    Set<Genre> findGenresByMovieId(@Param("id") Integer id);

    @Query("SELECT l FROM Language l WHERE l.movie.id = :id")
    Set<Language> findLanguageByMovieId(@Param("id") Integer id);

    @Query("SELECT p FROM Posters p WHERE p.movie.id = :id")
    Posters findPostersByMovieId(@Param("id") Integer id);

    @Query("SELECT r FROM Release r WHERE r.movie.id = :id")
    Set<Release> findReleaseByMovieId(@Param("id") Integer id);

    @Query("SELECT s FROM Studio s WHERE s.movie.id = :id")
    Set<Studio> findStudioByMovieId(@Param("id") Integer id);

    @Query("SELECT t FROM Theme t WHERE t.movie.id = :id")
    Set<Theme> findThemeByMovieId(@Param("id") Integer id);
}