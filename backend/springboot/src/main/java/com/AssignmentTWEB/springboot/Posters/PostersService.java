package com.AssignmentTWEB.springboot.Posters;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for business logic related to Posters.
 */
@Service
public class PostersService {

    @Autowired
    private PostersRepository postersRepository;

    /**
     * Retrieve the poster associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return the poster entity for the specified movie
     */
    public Posters getPostersByMovie(Integer id) {
        Movie movie = new Movie();
        movie.setId(id);

        return postersRepository.findByMovie(movie);

    }
}