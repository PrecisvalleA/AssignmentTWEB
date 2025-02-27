package com.AssignmentTWEB.springboot.controller;

import com.AssignmentTWEB.springboot.model.Movie;
import com.AssignmentTWEB.springboot.service.MovieService;
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
    public Optional<Movie> getMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    //Endpoint: add or update a film
    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    //Endpoint: delete a film by id
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        movieService.deleteMovie(id);
    }

    //Endpoint: search a film by name
    @GetMapping("/search")
    public List<Movie> findByName(@RequestParam String keyword) {
        return movieService.findMoviesByName(keyword);
    }

    //Endpoint: search film with rating >= value
    @GetMapping("/rating/{value}")
    public List<Movie> findByRating(@PathVariable double value) {
        return movieService.getMovieByRatingRange(value, 5);
    }

    //Endpoint: search film with duration >= value
    @GetMapping("/minute/{value}")
    public List<Movie> findByMinute(@PathVariable double value) {
        return movieService.getMoviesByDuration(value);
    }

    //Endpoint: search film with rating between 2 values
    @GetMapping("/ratingRange")
    public List<Movie> findByRatingRange(@RequestParam double min, @RequestParam double max) {
        return movieService.getMovieByRatingRange(min, max);
    }

    //Endpoint: search film with duration between 2 values
    @GetMapping("/durationRange")
    public List<Movie> findByDurationRange(@RequestParam double min, @RequestParam double max) {
        return movieService.getMoviesByDurationRange(min, max);
    }

    //Endpoint: search top films by rating
    @GetMapping("/top")
    public List<Movie> findTopMovies() {
        return movieService.findTopMovies();
    }

}
