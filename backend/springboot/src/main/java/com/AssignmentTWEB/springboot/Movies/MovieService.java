package com.AssignmentTWEB.springboot.Movies; // package class

import com.AssignmentTWEB.springboot.Actors.Actor;
import com.AssignmentTWEB.springboot.Countries.Country;
import com.AssignmentTWEB.springboot.Countries.CountryService;
import com.AssignmentTWEB.springboot.Crews.Crew;
import com.AssignmentTWEB.springboot.Crews.CrewService;
import com.AssignmentTWEB.springboot.Genres.Genre;
import com.AssignmentTWEB.springboot.Genres.GenreService;
import com.AssignmentTWEB.springboot.Languages.Language;
import com.AssignmentTWEB.springboot.OscarAwards.OscarAward;
import com.AssignmentTWEB.springboot.OscarAwards.OscarAwardRepository;
import com.AssignmentTWEB.springboot.OscarAwards.OscarAwardService;
import com.AssignmentTWEB.springboot.Posters.Posters;
import com.AssignmentTWEB.springboot.Posters.PostersService;
import com.AssignmentTWEB.springboot.Releases.Release;
import com.AssignmentTWEB.springboot.Releases.ReleaseService;
import com.AssignmentTWEB.springboot.Studios.Studio;
import com.AssignmentTWEB.springboot.Studios.StudioService;
import com.AssignmentTWEB.springboot.Themes.Theme;
import com.AssignmentTWEB.springboot.Themes.ThemeService;
import jakarta.persistence.EntityNotFoundException;
import com.AssignmentTWEB.springboot.Actors.ActorService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.AssignmentTWEB.springboot.Movies.MovieDetailsDTO;

@Service

public class MovieService{

    // connection to the repository to access to the database
    private final MovieRepository movieRepository;

    private final OscarAwardService oscarAwardService;

    private final ActorService actorService;

    private final CrewService crewService;

    private final CountryService countryService;

    private final GenreService genreService;

    private final ReleaseService releaseService;

    private final StudioService studioService;

    private final ThemeService themeService;

    private final PostersService postersService;

    @Autowired
    public MovieService(MovieRepository movieRepository,OscarAwardService oscarAwardService, ActorService actorService, CrewService crewService, CountryService countryService, GenreService genreService, ReleaseService releaseService, StudioService studioService, ThemeService themeService, PostersService postersService) {
        this.movieRepository = movieRepository;
        this.oscarAwardService = oscarAwardService;
        this.actorService = actorService;
        this.crewService = crewService;
        this.countryService = countryService;
        this.genreService = genreService;
        this.releaseService = releaseService;
        this.studioService = studioService;
        this.themeService = themeService;
        this.postersService = postersService;
    }


    //find all films from the database
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    //find movie by ID
    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }


    //function to add a new film or update a film
    public Movie saveMovie(Movie movie){
        return movieRepository.save(movie);
    }


    //delete a movie by id
    public void deleteMovie(Integer id) {movieRepository.deleteById(id);}


    //search a movie by a keyword insert by user
    public Page<MoviePoster> findMoviesByName(String keyword, Pageable pageable) {return movieRepository.findByNameContainingIgnoreCase(keyword, pageable);}


    public Page<Movie> getPaginatedMoviesPage(Pageable pageable) {
        return movieRepository.findMoviesWithPosters(pageable);
    }


    public Page<MoviePoster> filterMovies(
            Double minRating, Double maxRating,
            String minDate,   String maxDate,
            Double minDuration, Double maxDuration,
            String genre, Pageable pageable) {
        return movieRepository.findWithAllFilters(
                minRating, maxRating,
                minDate,   maxDate,
                minDuration, maxDuration,
                genre, pageable
        );
    }

    public Optional<MovieDetailsDTO> getMovieDetailsById(Integer id_movie) {
        Movie movieById = movieRepository.findMovieById(id_movie);

        if (movieById != null) {
            Movie movie = movieRepository.findMovieById(id_movie);
            Set<Actor> actors = movieRepository.findActorsByMovieId(id_movie);
            Set<Crew> crews = movieRepository.findCrewsByMovieId(id_movie);
            Set<Country> countries = movieRepository.findCountriesByMovieId(id_movie);
            Set<Genre> genres = movieRepository.findGenresByMovieId(id_movie);
            Set<Language> languages = movieRepository.findLanguageByMovieId(id_movie);
            Posters posters = movieRepository.findPostersByMovieId(id_movie);
            Set<Release> releases = movieRepository.findReleaseByMovieId(id_movie);
            Set<Studio> studios = movieRepository.findStudioByMovieId(id_movie);
            Set<Theme> themes = movieRepository.findThemeByMovieId(id_movie);
            List<OscarAward> oscars = oscarAwardService.getByMovieName(movie.getName());


            MovieDetailsDTO detailsDTO = new MovieDetailsDTO(
                    movie,
                    actors,
                    crews,
                    countries,
                    genres,
                    posters,
                    languages,
                    releases,
                    studios,
                    themes,
                    oscars
            );

            return Optional.of(detailsDTO);
        }

        return Optional.empty();
    }

}