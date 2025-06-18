package com.AssignmentTWEB.springboot.Actors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/movies/{id}")
    public List<Actor> getAllActorsByMovie(@PathVariable Integer id) {
        return actorService.getActorsByMovie(id);
    }

    @GetMapping("/all")
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }
}
