package com.AssignmentTWEB.springboot.Releases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

/**
 * REST Controller for managing Release-related operations.
 */
@RestController
@RequestMapping("/releases")
public class ReleaseController {

    @Autowired
    private ReleaseService releaseService;

    /**
     * Retrieve all releases associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of releases for the movie
     */
    @Operation(summary = "Get releases by movie ID", description = "Returns a list of all releases for the specified movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Releases retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/movies/{id}")
    public List<Release> getReleasesByMovie(@PathVariable Integer id) {
        return releaseService.getReleaseByMovie(id);
    }
}
