package com.AssignmentTWEB.springboot.service; // package class

import com.AssignmentTWEB.springboot.model.Movie; // import movie entity which represents "movies" table
import com.AssignmentTWEB.springboot.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<Movie> getMovieById(int id) {
        return movieRepository.findById(id);
    }

    //function to add a new film or update a film
    public Movie saveMovie(Movie movie){
        return movieRepository.save(movie);
    }

    //delete a movie by id
    public void deleteMovie(int id) {movieRepository.deleteById(id);}

    //search a movie by a keyword insert by user
    public List<Movie> findMoviesByName(String keyword) {return movieRepository.findByNameContaining(keyword);}

    // find movies with duration >= to a value insert by user
    public List<Movie> getMoviesByDuration(double minute) {
        return movieRepository.findByMinuteGreaterThanEqual(minute);
    }

    //find movies with rating between two values insert by user
    public List<Movie> getMovieByRatingRange(double min, double max) {
        return movieRepository.findByRatingBetween(min, max);
    }

    //find movies with duration between two values insert by user
    public List<Movie> getMoviesByDurationRange(double min, double max) {
        return movieRepository.findByMinuteBetween(min, max);
    }

    //find best movies by rating
    public List<Movie> findTopMovies() {
        return movieRepository.findAllByOrderByRatingDesc();
    }
}