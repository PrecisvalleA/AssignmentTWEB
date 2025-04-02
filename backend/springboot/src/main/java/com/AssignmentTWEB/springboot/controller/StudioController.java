package com.AssignmentTWEB.springboot.controller;

import com.AssignmentTWEB.springboot.model.Studio;
import com.AssignmentTWEB.springboot.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studios")
public class StudioController {
    @Autowired
    private StudioService studioService;

    @GetMapping("/movies/{id_movie}")
    public List<String> getAllStudios(@PathVariable Integer id_movie) {
        return studioService.getStudioByMovie(id_movie);
    }
}
