package com.AssignmentTWEB.springboot.Actors;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    //get all actors
    public List<String> getActorsByMovie(Integer id_movie) {

        Movie movie = new Movie();
        movie.setId_movie(id_movie);

        List<Actor> actors = actorRepository.findByMovie(movie);

        return actors.stream()
                .map(actor -> actor.getId_actor().getName() + " - " + actor.getId_actor().getRole())
                .collect(Collectors.toList());
    }
}