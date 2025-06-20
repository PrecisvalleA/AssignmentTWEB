package com.AssignmentTWEB.springboot.Languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

/**
 * REST Controller for managing Language-related operations.
 */
@RestController
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    /**
     * Retrieve all languages associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of languages for the movie
     */
    @Operation(summary = "Get languages by movie ID", description = "Returns a list of languages associated with the specified movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Languages retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("movies/{id}")
    public List<Language> getAllLanguages(@PathVariable Integer id) {
        return languageService.getLanguageByMovie(id);
    }
}
