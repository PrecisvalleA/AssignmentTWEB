package com.AssignmentTWEB.springboot.Releases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/releases")
public class ReleaseController {

    @Autowired
    private ReleaseService releaseService;

    @GetMapping("/movies/{id_movie}")
    public List<String> getReleasesByMovie(@PathVariable Integer id_movie) {
        return releaseService.getReleaseByMovie(id_movie);
    }
}
