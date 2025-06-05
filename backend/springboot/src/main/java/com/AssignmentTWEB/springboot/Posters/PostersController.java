
package com.AssignmentTWEB.springboot.Posters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posters")
public class PostersController {

    @Autowired
    private PostersService postersService;

    @GetMapping("/movies/{id_movie}")
    public Posters getAllposters(@PathVariable Integer id_movie) {
        return postersService.getPostersByMovie(id_movie);
    }
}
