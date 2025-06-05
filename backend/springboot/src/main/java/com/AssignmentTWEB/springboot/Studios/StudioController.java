package com.AssignmentTWEB.springboot.Studios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studios")
public class StudioController {
    @Autowired
    private StudioService studioService;

    @GetMapping("/movies/{id_movie}")
    public List<Studio> getAllStudios(@PathVariable Integer id_movie) {
        return studioService.getStudioByMovie(id_movie);
    }
}
