package com.AssignmentTWEB.springboot.Releases;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for business logic related to Releases.
 */
@Service
public class ReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

    /**
     * Retrieve all releases associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of releases for the specified movie
     */
    public List<Release> getReleaseByMovie(Integer id) {

        Movie movie = new Movie();
        movie.setId(id);

        return releaseRepository.findByMovie(movie);
    }
}

