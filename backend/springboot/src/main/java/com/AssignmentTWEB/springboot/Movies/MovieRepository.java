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
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List; // list is necessary for function which return many results
import java.util.Optional;
import java.util.Set;

@Repository

public interface MovieRepository extends JpaRepository<Movie, Integer> {//extends JpaRepository for Movie entity with Integer ID

    @Query("SELECT m, p.link AS posterURL FROM Movie m LEFT JOIN Posters p ON m.id_movie = p.movie.id_movie")
    Page<Movie> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT m, p.link AS posterUrl \n" +
            "FROM Movie m \n" +
            "LEFT JOIN Posters p \n" +
            "ON m.id_movie = p.movie.id_movie \n" +
            "WHERE m.rating IS NOT NULL \n")
    Page<Movie> findByRatingGreaterThanEqual(Double min, Pageable pageable);


    @Query("SELECT m, p.link AS posterUrl \n" +
            "FROM Movie m \n" +
            "LEFT JOIN Posters p \n" +
            "ON m.id_movie = p.movie.id_movie \n" +
            "WHERE m.rating IS NOT NULL \n")
    Page<Movie> findByRatingLessThanEqual(Double max, Pageable pageable);


    @Query("SELECT m, p.link AS posterUrl \n" +
            "FROM Movie m \n" +
            "LEFT JOIN Posters p \n" +
            "ON m.id_movie = p.movie.id_movie \n" +
            "WHERE m.rating IS NOT NULL \n")
    Page<Movie> findByRatingBetween(Double min, Double max, Pageable pageable); //return all film where rating is between 2 values


    @Query("SELECT m, p.link AS posterUrl \n" +
            "FROM Movie m \n" +
            "LEFT JOIN Posters p \n" +
            "ON m.id_movie = p.movie.id_movie \n" +
            "WHERE m.date IS NOT NULL \n")
    Page<Movie> findByDateBetween(String minDate, String maxDate, Pageable pageable);


    @Query("SELECT m, p.link AS posterUrl \n" +
            "FROM Movie m \n" +
            "LEFT JOIN Posters p \n" +
            "ON m.id_movie = p.movie.id_movie \n" +
            "WHERE m.date IS NOT NULL \n")
    Page<Movie> findByDateGreaterThanEqual(String minDate, Pageable pageable);


    @Query("SELECT m, p.link AS posterUrl \n" +
            "FROM Movie m \n" +
            "LEFT JOIN Posters p \n" +
            "ON m.id_movie = p.movie.id_movie \n" +
            "WHERE m.date IS NOT NULL \n")
    Page<Movie> findByDateLessThanEqual(String maxDate, Pageable pageable);


    @Query("SELECT m, p.link AS posterUrl \n" +
            "FROM Movie m \n" +
            "LEFT JOIN Posters p \n" +
            "ON m.id_movie = p.movie.id_movie \n" +
            "WHERE m.minute IS NOT NULL \n")
    Page<Movie> findByDurationBetween(Double minDuration, Double maxDuration, Pageable pageable); //return all film where minute is between 2 values


    @Query("SELECT m, p.link AS posterUrl \n" +
            "FROM Movie m \n" +
            "LEFT JOIN Posters p \n" +
            "ON m.id_movie = p.movie.id_movie \n" +
            "WHERE m.minute IS NOT NULL \n")
    Page<Movie> findByDurationGreaterThanEqual(Double minDuration, Pageable pageable); //return all film where minute is >= than a value


    @Query("SELECT m, p.link AS posterUrl \n" +
            "FROM Movie m \n" +
            "LEFT JOIN Posters p \n" +
            "ON m.id_movie = p.movie.id_movie \n" +
            "WHERE m.minute IS NOT NULL \n")
    Page<Movie> findByDurationLessThanEqual(Double maxDuration, Pageable pageable);


    @Query("SELECT m FROM Movie m WHERE m.id_movie = :id_movie")
    Movie findMovieById(@Param("id_movie") Integer id_movie);


    @Query("SELECT m, p.link AS posterUrl \n" +
            "FROM Movie m \n" +
            "LEFT JOIN Posters p \n" +
            "ON m.id_movie = p.movie.id_movie \n" +
            "WHERE m.rating IS NOT NULL \n")
    Page<Movie> findMoviesWithPosters(Pageable pageable);

    @Query("SELECT a FROM Actor a WHERE a.movie.id_movie = :id_movie ")
    Set<Actor> findActorsByMovieId(@Param("id_movie") Integer id_movie);

    @Query("SELECT c FROM Crew c WHERE c.movie.id_movie = :id_movie")
    Set<Crew> findCrewsByMovieId(@Param("id_movie") Integer id_movie);

    @Query("SELECT co FROM Country co WHERE co.movie.id_movie = :id_movie")
    Set<Country> findCountriesByMovieId(@Param("id_movie") Integer id_movie);

    @Query("SELECT g FROM Genre g WHERE g.movie.id_movie = :id_movie")
    Set<Genre> findGenresByMovieId(@Param("id_movie") Integer id_movie);

    @Query("SELECT l FROM Language l WHERE l.movie.id_movie = :id_movie")
    Set<Language> findLanguageByMovieId(@Param("id_movie") Integer id_movie);

    @Query("SELECT p FROM Posters p WHERE p.movie.id_movie = :id_movie")
    Posters findPostersByMovieId(@Param("id_movie") Integer id_movie);

    @Query("SELECT r FROM Release r WHERE r.movie.id_movie = :id_movie")
    Set<Release> findReleaseByMovieId(@Param("id_movie") Integer id_movie);

    @Query("SELECT s FROM Studio s WHERE s.movie.id_movie = :id_movie")
    Set<Studio> findStudioByMovieId(@Param("id_movie") Integer id_movie);

    @Query("SELECT t FROM Theme t WHERE t.movie.id_movie = :id_movie")
    Set<Theme> findThemeByMovieId(@Param("id_movie") Integer id_movie);
}