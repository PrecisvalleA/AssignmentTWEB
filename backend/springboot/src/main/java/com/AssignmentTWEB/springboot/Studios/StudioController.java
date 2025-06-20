package com.AssignmentTWEB.springboot.Studios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

/**
 * REST Controller for managing Studio-related operations.
 */
@RestController
@RequestMapping("/studios")
public class StudioController {
    @Autowired
    private StudioService studioService;

    /**
     * Retrieve all studios associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of studios for the movie
     */
    @Operation(summary = "Get studios by movie ID", description = "Returns a list of all studios involved in the production of the specified movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Studios retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/movies/{id}")
    public List<Studio> getAllStudios(@PathVariable Integer id) {
        return studioService.getStudioByMovie(id);
    }
}
