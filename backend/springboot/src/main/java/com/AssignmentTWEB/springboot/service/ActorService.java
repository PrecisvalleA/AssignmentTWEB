package com.AssignmentTWEB.springboot.service;


import com.AssignmentTWEB.springboot.model.Actor;
import com.AssignmentTWEB.springboot.model.Movie;
import com.AssignmentTWEB.springboot.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    //get all actors
    public List<String> getActorsByMovie(Integer id_movie) {

        Movie movie = new Movie();
        movie.setId_movie(id_movie);

        List<Actor> actors = actorRepository.findByMovie(movie);

        return actors.stream().map(Actor -> Actor.getName() + " - " + Actor.getRole)
                .collect(Collectors.toList()); // stream().map() is used to extract name and role and format them as String
    }


}
