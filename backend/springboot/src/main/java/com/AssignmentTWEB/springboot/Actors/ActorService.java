package com.AssignmentTWEB.springboot.Actors;

import com.AssignmentTWEB.springboot.Movies.Movie;
import com.AssignmentTWEB.springboot.Movies.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private MovieRepository movieRepository;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    //get all actors
    public List<Actor> getActorsByMovie(Integer id_movie) {

        Movie movie = movieRepository.findById(id_movie).orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        movie.setId_movie(id_movie);

        return actorRepository.findByMovie(movie);

    }
}