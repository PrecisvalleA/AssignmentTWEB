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
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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


    //Endpoint: to get all film
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    //Endpoint: to get a film by ID
    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailsDTO> getMovieDetailsById(@PathVariable Integer id) {
        Optional<MovieDetailsDTO> details = movieService.getMovieDetailsById(id);
        return details.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    //Endpoint: add or update a film
    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    //Endpoint: delete a film by id
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        movieService.deleteMovie(id);
    }

    //Endpoint: search a film by name
    @GetMapping("/search/{keyword}")
    public List<Movie> findByName(@PathVariable String keyword) {
        return movieService.findMoviesByName(keyword);
    }

    @GetMapping("/date")
    public List<Movie> findByDate(@RequestParam String date) {
        return movieService.findMoviesByDate(date);
    }

    //Endpoint: search film with rating >= value
    @GetMapping("/rating/{value}")
    public List<Movie> findByRating(@PathVariable Double value) {
        return movieService.getMovieByRatingRange(value, 5.0);
    }

    //Endpoint: search film with duration >= value
    @GetMapping("/minute/{value}")
    public List<Movie> findByMinute(@PathVariable Double value) {
        return movieService.getMoviesByDuration(value);
    }

    //Endpoint: search film with rating between 2 values
    @GetMapping("/ratingRange")
    public List<Movie> findByRatingRange(@RequestParam Double min, @RequestParam Double max) {
        return movieService.getMovieByRatingRange(min, max);
    }

    //Endpoint: search film with duration between 2 values
    @GetMapping("/durationRange")
    public List<Movie> findByDurationRange(@RequestParam Double min, @RequestParam Double max) {
        return movieService.getMoviesByDurationRange(min, max);
    }

    //Endpoint: search top films by rating
    @GetMapping("/top")
    public List<Movie> findTopMovies() {
        return movieService.findTopMovies();
    }

    @GetMapping("/paginated")
    public Page<Movie> getPaginatedMovies(
           @PageableDefault(size = 12, sort = "rating") Pageable pageable) {
        return movieService.getPaginatedMoviesPage(pageable);
    }

}
