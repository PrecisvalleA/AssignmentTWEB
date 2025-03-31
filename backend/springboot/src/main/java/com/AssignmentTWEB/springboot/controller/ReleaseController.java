package com.AssignmentTWEB.springboot.controller;

import com.AssignmentTWEB.springboot.model.Release;
import com.AssignmentTWEB.springboot.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/releases")
public class ReleaseController {

    @Autowired
    private ReleaseService releaseService;

    @GetMapping
    public List<Release> getAllReleases() {
        return releaseService.getAllReleases();
    }

    @GetMapping("/movies/{id_movie}")
    public List<Release> getReleasesByMovie(@PathVariable Long id_movie) {
        return releaseService.getReleasesByMovie(id_movie);
    }
}
