package com.AssignmentTWEB.springboot.Studios;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for business logic related to Studios.
 */
@Service
public class StudioService {
    @Autowired
    private StudioRepository studioRepository;

    /**
     * Retrieve all studios associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of studios for the specified movie
     */
    public List<Studio> getStudioByMovie(Integer id) {

        Movie movie = new Movie();
        movie.setId(id);

        return studioRepository.findByMovie(movie);
    }
}
