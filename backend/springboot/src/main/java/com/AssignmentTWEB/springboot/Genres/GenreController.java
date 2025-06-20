
package com.AssignmentTWEB.springboot.Genres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

/**
 * REST Controller for managing Genre-related operations.
 */
@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    /**
     * Retrieve all genres associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of genres for the movie
     */
    @Operation(summary = "Get genres by movie ID", description = "Returns a list of genres associated with the specified movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genres retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/movies/{id}")
    public List<Genre> getAllGenres(@PathVariable Integer id) {
        return genreService.getGenreById(id);
    }
}
