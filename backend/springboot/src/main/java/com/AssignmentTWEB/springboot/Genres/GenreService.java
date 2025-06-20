package com.AssignmentTWEB.springboot.Genres;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class responsible for business logic related to Genres.
 */

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    /**
     * Retrieve all genres associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of genres for the specified movie
     */
    public List<Genre> getGenreById(Integer id) {

        Movie movie = new Movie();
        movie.setId(id);

        return genreRepository.findByMovie(movie);
    }
}