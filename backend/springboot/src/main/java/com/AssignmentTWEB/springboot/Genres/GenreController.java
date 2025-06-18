
package com.AssignmentTWEB.springboot.Genres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/movies/{id}")
    public List<Genre> getAllGenres(@PathVariable Integer id) {
        return genreService.getGenreById(id);
    }
}
