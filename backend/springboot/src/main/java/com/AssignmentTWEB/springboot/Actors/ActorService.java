package com.AssignmentTWEB.springboot.Actors;

import com.AssignmentTWEB.springboot.Movies.Movie;
import com.AssignmentTWEB.springboot.Movies.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class responsible for business logic related to Actors.
 * Interacts with the ActorRepository and MovieRepository.
 */
@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private MovieRepository movieRepository;

    /**
     * Retrieve all actors from the database.
     *
     * @return list of all Actor entities
     */
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    /**
     * Retrieve all actors associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of actors for the given movie
     * @throws EntityNotFoundException if the movie with the given ID is not found
     */
    public List<Actor> getActorsByMovie(Integer id) {

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        movie.setId(id);

        return actorRepository.findByMovie(movie);

    }
}