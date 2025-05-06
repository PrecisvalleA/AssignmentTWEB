
package com.AssignmentTWEB.springboot.Genres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/movies/{id_movie}")
    public List<String> getAllGenres(@PathVariable Integer id_movie) {
        return genreService.getGenreById(id_movie);
    }
}
