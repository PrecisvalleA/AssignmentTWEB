package com.AssignmentTWEB.springboot.Crews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

/**
 * REST Controller for managing Crew-related operations.
 */
@RestController
@RequestMapping("/crew")
public class CrewController {

    @Autowired
    private CrewService crewService;


    /**
     * Retrieve all crew members associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of crew members for the movie
     */
    @Operation(summary = "Get crew members by movie ID", description = "Returns a list of all crew members involved in the specified movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crew members retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/movies/{id}")
    public List<Crew> getAllCrews(@PathVariable Integer id) {
        return crewService.getCrewsByMovie(id);
    }
}
