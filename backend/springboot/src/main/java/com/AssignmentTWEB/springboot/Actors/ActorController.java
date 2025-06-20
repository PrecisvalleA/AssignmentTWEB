package com.AssignmentTWEB.springboot.Actors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;


/**
 * REST Controller for managing Actor-related operations.
 */
@RestController
@RequestMapping("/actors")
public class ActorController {


    @Autowired
    private ActorService actorService;

    /**
     * Retrieve all actors associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of actors for the given movie
     */
    @Operation(summary = "Get actors by movie ID", description = "Returns a list of all actors who performed in the specified movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actors retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/movies/{id}")
    public List<Actor> getAllActorsByMovie(@PathVariable Integer id) {
        return actorService.getActorsByMovie(id);
    }

    /**
     * Retrieve all actors from the database.
     *
     * @return list of all actors
     */
    @Operation(summary = "Get all actors", description = "Returns a list of all actors available.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actors retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/all")
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }
}
