package com.AssignmentTWEB.springboot.OscarAwards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

/**
 * REST Controller for managing Oscar Award operations.
 */
@RestController
@RequestMapping("/oscarAwards")
public class OscarAwardController {
    @Autowired
    private OscarAwardService oscarAwardService;

    /**
     * Retrieve all Oscar awards from the database.
     *
     * @return list of all Oscar awards
     */
    @Operation(summary = "Get all Oscar awards", description = "Returns a list of all Oscar award entries.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Oscar awards retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<OscarAward> getAllOscarAwards() {
        return oscarAwardService.getAll();
    }

    /**
     * Retrieve Oscar awards by film name.
     *
     * @param name the name of the film
     * @return list of Oscar awards for the specified film
     */
    @Operation(summary = "Get Oscar awards by film name", description = "Returns a list of Oscar awards for a specific film.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Oscar awards retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Film not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/movie/{name}")
    public List<OscarAward> getOscarAwardsByMovie(@PathVariable String name) {
        return oscarAwardService.getByMovieName(name);
    }
}
