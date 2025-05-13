package com.AssignmentTWEB.springboot.Movies; // package class

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class MovieService{

    // connection to the repository to access to the database
    @Autowired
    private MovieRepository movieRepository;

    //find all films from the database
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    //find movie by ID
    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    //function to add a new film or update a film
    public Movie saveMovie(Movie movie){
        return movieRepository.save(movie);
    }

    //delete a movie by id
    public void deleteMovie(Integer id) {movieRepository.deleteById(id);}

    //search a movie by a keyword insert by user
    public List<Movie> findMoviesByName(String keyword) {return movieRepository.findByNameContainingIgnoreCase(keyword);}

    //search movies with date >= than a value insert by user
    public List<Movie> findMoviesByDate(String date) {return movieRepository.findByDateContaining(date);}

    // find movies with duration >= to a value insert by user
    public List<Movie> getMoviesByDuration(Double minute) {
        return movieRepository.findByMinuteGreaterThanEqual(minute);
    }

    //find movies with rating between two values insert by user
    public List<Movie> getMovieByRatingRange(Double min, Double max) {
        return movieRepository.findByRatingBetween(min, max);
    }

    //find movies with duration between two values insert by user
    public List<Movie> getMoviesByDurationRange(Double min, Double max) {
        return movieRepository.findByMinuteBetween(min, max);
    }

    //find best movies by rating
    public List<Movie> findTopMovies() {
        return movieRepository.findAllByOrderByRatingDesc();
    }

    public Page<Movie> getPaginatedMoviesPage(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        return movieRepository.findMoviesReleasedBefore2026(pageable);
    }



}