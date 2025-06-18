
package com.AssignmentTWEB.springboot.Posters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posters")
public class PostersController {

    @Autowired
    private PostersService postersService;

    @GetMapping("/movies/{id}")
    public Posters getAllposters(@PathVariable Integer id) {
        return postersService.getPostersByMovie(id);
    }
}
