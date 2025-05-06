package com.AssignmentTWEB.springboot.Actors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/movies/{id_movie}")
    public List<String> getAllActorsByMovie(@PathVariable Integer id_movie) {
        return actorService.getActorsByMovie(id_movie);
    }

    @GetMapping("/all")
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }
}
