package com.AssignmentTWEB.springboot.Movies;

import com.AssignmentTWEB.springboot.Actors.ActorService;
import com.AssignmentTWEB.springboot.Countries.CountryService;
import com.AssignmentTWEB.springboot.Crews.CrewService;
import com.AssignmentTWEB.springboot.Genres.GenreService;
import com.AssignmentTWEB.springboot.OscarAwards.OscarAward;
import com.AssignmentTWEB.springboot.Posters.Posters;
import com.AssignmentTWEB.springboot.OscarAwards.OscarAwardRepository;
import com.AssignmentTWEB.springboot.Posters.PostersRepository;
import com.AssignmentTWEB.springboot.Posters.PostersService;
import com.AssignmentTWEB.springboot.Releases.ReleaseService;
import com.AssignmentTWEB.springboot.Studios.StudioService;
import com.AssignmentTWEB.springboot.Themes.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired private OscarAwardRepository oscarAwardRepository;

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
    public Optional<Movie> getMovieById(@PathVariable Integer id) {
        return movieService.getMovieById(id);
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

    @GetMapping("/details/{id}")
    public Map<String, Object> getMovieDetails(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        Optional<Movie> optionalMovie = movieService.getMovieById(id);
        if (optionalMovie.isEmpty()) {
            response.put("error", "Movie not found");
            return response;
        }

        Movie movie = optionalMovie.get();
        response.put("movie", movie);
        response.put("actors", actorService.getActorsByMovie(id));
        response.put("crew", crewService.getCrewsByMovie(id));
        response.put("countries", countryService.getCountriesByMovie(id));
        response.put("genres", genreService.getGenreById(id));
        response.put("releases", releaseService.getReleaseByMovie(id));
        response.put("studios", studioService.getStudioByMovie(id));
        response.put("themes", themeService.getThemeById(id));
        response.put("posters", postersService.getPostersByMovie(id));

        List<OscarAward> oscars = oscarAwardRepository.findByFilm(movie.getName());
        response.put("oscars", oscars);

        return response;
    }

    @GetMapping("/details/top")
    public List<Map<String, Object>> getTopMovieDetails() {
        List<Movie> topMovies = movieService.findTopMovies();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Movie movie : topMovies) {
            Map<String, Object> details = new HashMap<>();
            details.put("movie", movie);

            Posters poster = posterRepository.findByMovie(movie);
            if (poster != null) {
                details.put("poster", poster.getId_posters().getLink());
            }

            result.add(details);
        }

        return result;
    }

    @GetMapping("/paginated")
    public List<Map<String, Object>> getPaginatedMovies(
            @RequestParam(defaultValue = "12") int limit,
            @RequestParam(defaultValue = "0") int page) {
        Page<Movie> pageResult = movieService.getPaginatedMoviesPage(limit, page);

        List<Map<String, Object>> result = new ArrayList<>();
        for (Movie movie : pageResult.getContent()) {
            Map<String, Object> data = new HashMap<>();
            data.put("movie", movie);
            data.put("posters", postersService.getPostersByMovie(movie.getId_movie()));
            result.add(data);
        }

        return result;
    }

}
