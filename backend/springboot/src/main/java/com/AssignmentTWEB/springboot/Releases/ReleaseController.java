package com.AssignmentTWEB.springboot.Releases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/releases")
public class ReleaseController {

    @Autowired
    private ReleaseService releaseService;

    @GetMapping("/movies/{id}")
    public List<Release> getReleasesByMovie(@PathVariable Integer id) {
        return releaseService.getReleaseByMovie(id);
    }
}
