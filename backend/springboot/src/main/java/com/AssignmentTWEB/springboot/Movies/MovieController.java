package com.AssignmentTWEB.springboot.Movies;

import com.AssignmentTWEB.springboot.Movies.MovieDetailsDTO;
import com.AssignmentTWEB.springboot.Actors.ActorService;
import com.AssignmentTWEB.springboot.Countries.CountryService;
import com.AssignmentTWEB.springboot.Crews.CrewService;
import com.AssignmentTWEB.springboot.Genres.GenreService;
import com.AssignmentTWEB.springboot.OscarAwards.OscarAward;
import com.AssignmentTWEB.springboot.Posters.Posters;
import com.AssignmentTWEB.springboot.OscarAwards.OscarAwardService;
import com.AssignmentTWEB.springboot.Posters.PostersRepository;
import com.AssignmentTWEB.springboot.Posters.PostersService;
import com.AssignmentTWEB.springboot.Releases.ReleaseService;
import com.AssignmentTWEB.springboot.Studios.StudioService;
import com.AssignmentTWEB.springboot.Themes.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.*;

/**
 * REST Controller for managing Movie entities and related operations.
 */
@RestController
@RequestMapping("/movies") //endpoint
public class MovieController {

    @Autowired private MovieService movieService;

    @Autowired private ActorService actorService;

    @Autowired private CrewService crewService;

    @Autowired private CountryService countryService;

    @Autowired private PostersRepository posterRepository;

    @Autowired private OscarAwardService oscarAwardService;

    @Autowired private GenreService genreService;

    @Autowired private ReleaseService releaseService;

    @Autowired private StudioService studioService;

    @Autowired private ThemeService themeService;

    @Autowired private PostersService postersService;


    /**
     * Retrieve all movies in the database.
     */
    @Operation(summary = "Retrieve all movies", description = "Returns a complete list of all movies stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    /**
     * Retrieve full movie details by movie ID.
     */
    @Operation(summary = "Retrieve movie details by ID", description = "Returns complete movie details including related entities (actors, crew, countries, etc.).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie details received successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailsDTO> getMovieDetailsById(@PathVariable Integer id) {
        Optional<MovieDetailsDTO> details = movieService.getMovieDetailsById(id);
        return details.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    /**
     * Add a new movie or update an existing one.
     */
    @Operation(summary = "Create or update a movie", description = "Adds a new movie or updates an existing one based on the provided data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid movie data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    /**
     * Delete a movie by ID.
     */
    @Operation(summary = "Delete a movie by ID", description = "Deletes the movie corresponding to the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        movieService.deleteMovie(id);
    }

    /**
     * Search movies by keyword in the name.
     */
    @Operation(summary = "Search movies by name", description = "Performs a case-insensitive search for movies containing the given keyword in their title.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/search/{keyword}")
    public Page<MoviePoster> findByName(@PathVariable String keyword, @PageableDefault(size = 12) Pageable pageable) {
        return movieService.findMoviesByName(keyword, pageable);
    }

    /**
     * Retrieve filtered and paginated list of movies.
     */
    @Operation(summary = "Filter and paginate movies", description = "Returns paginated movies filtered by rating, release date, duration, and genre.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies filtered and paginated successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/paginated")
    public Page<MoviePoster> getPaginatedMovies(
           @RequestParam(required = false) Double minRating,
           @RequestParam(required = false) Double maxRating,
           @RequestParam(required = false) Double minDuration,
           @RequestParam(required = false) Double maxDuration,
           @RequestParam(required = false) String minDate,
           @RequestParam(required = false) String maxDate,
           @RequestParam(required = false) String genre,
           @PageableDefault(size = 12, sort = "rating", direction = Sort.Direction.DESC) Pageable pageable){
        return movieService.filterMovies(minRating, maxRating, minDate,maxDate, minDuration, maxDuration,  genre, pageable);
    }

}
