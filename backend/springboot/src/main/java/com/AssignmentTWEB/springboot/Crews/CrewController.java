package com.AssignmentTWEB.springboot.Crews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crew")
public class CrewController {

    @Autowired
    private CrewService crewService;

    @GetMapping("/movies/{id_movie}")
    public List<Crew> getAllCrews(@PathVariable Integer id_movie) {
        return crewService.getCrewsByMovie(id_movie);
    }
}
