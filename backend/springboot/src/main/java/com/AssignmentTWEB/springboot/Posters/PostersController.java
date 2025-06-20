
package com.AssignmentTWEB.springboot.Posters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * REST Controller for managing Poster-related operations.
 */
@RestController
@RequestMapping("/posters")
public class PostersController {

    @Autowired
    private PostersService postersService;

    /**
     * Retrieve the poster associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return the poster for the movie
     */
    @Operation(summary = "Get poster by movie ID", description = "Returns the poster image associated with the specified movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Poster retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Poster not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/movies/{id}")
    public Posters getAllposters(@PathVariable Integer id) {
        return postersService.getPostersByMovie(id);
    }
}
