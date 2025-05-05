
package com.AssignmentTWEB.springboot.controller;

import com.AssignmentTWEB.springboot.model.Posters;
import com.AssignmentTWEB.springboot.service.PostersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posters")
public class PostersController {

    @Autowired
    private PostersService postersService;

    @GetMapping("/movies/{id_movie}")
    public String getAllposters(@PathVariable Integer id_movie) {
        return postersService.getPostersByMovie(id_movie);
    }
}
