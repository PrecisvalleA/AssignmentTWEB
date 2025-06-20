package com.AssignmentTWEB.springboot.Themes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

/**
 * REST Controller for managing Theme-related operations.
 */
@RestController
@RequestMapping("/themes")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    /**
     * Retrieve all themes associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of themes for the movie
     */
    @Operation(summary = "Get themes by movie ID", description = "Returns a list of all themes for the specified movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Themes retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/movies/{id}")
    public List<Theme> getAllThemes(@PathVariable Integer id) {
        return themeService.getThemeById(id);
    }
}