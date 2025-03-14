package com.AssignmentTWEB.springboot.controller;

import com.AssignmentTWEB.springboot.model.Movie;
import com.AssignmentTWEB.springboot.service.MovieService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies") //endpoint
public class MovieController {

    @Autowired
    private MovieService movieService;

    //Endpoint: to get all film
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    //Endpoint: to get a film by ID
    @GetMapping("/{id}")
    public Optional<Movie> getMovieById(@PathVariable Integer id) {
        return movieService.getMovieById(id);
    }

    //Endpoint: add or update a film
    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    //Endpoint: delete a film by id
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        movieService.deleteMovie(id);
    }

    //Endpoint: search a film by name
    @GetMapping("/search")
    public List<Movie> findByName(@RequestParam String keyword) {
        return movieService.findMoviesByName(keyword);
    }

    @GetMapping("/date")
    public List<Movie> findByDate(@RequestParam String date) {
        return movieService.findMoviesByDate(date);
    }

    //Endpoint: search film with rating >= value
    @GetMapping("/rating/{value}")
    public List<Movie> findByRating(@PathVariable Double value) {
        return movieService.getMovieByRatingRange(value, 5.0);
    }

    //Endpoint: search film with duration >= value
    @GetMapping("/minute/{value}")
    public List<Movie> findByMinute(@PathVariable Double value) {
        return movieService.getMoviesByDuration(value);
    }

    //Endpoint: search film with rating between 2 values
    @GetMapping("/ratingRange")
    public List<Movie> findByRatingRange(@RequestParam Double min, @RequestParam Double max) {
        return movieService.getMovieByRatingRange(min, max);
    }

    //Endpoint: search film with duration between 2 values
    @GetMapping("/durationRange")
    public List<Movie> findByDurationRange(@RequestParam Double min, @RequestParam Double max) {
        return movieService.getMoviesByDurationRange(min, max);
    }

    //Endpoint: search top films by rating
    @GetMapping("/top")
    public List<Movie> findTopMovies() {
        return movieService.findTopMovies();
    }

}
