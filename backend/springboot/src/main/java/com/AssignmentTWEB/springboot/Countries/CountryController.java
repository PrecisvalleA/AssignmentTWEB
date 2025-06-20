package com.AssignmentTWEB.springboot.Countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

/**
 * REST Controller for managing Country-related operations.
 */
@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    /**
     * Retrieve all countries associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of countries for the given movie
     */
    @Operation(summary = "Get countries by movie ID", description = "Returns a list of countries where the selected movie was produced or distributed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Countries retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/movies/{id}")
    public List<Country> getAllCountries(@PathVariable Integer id) {
        return countryService.getCountriesByMovie(id);
    }
}
