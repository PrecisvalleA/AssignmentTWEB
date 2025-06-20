package com.AssignmentTWEB.springboot.Crews;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for business logic related to Crew members.
 */
@Service
public class CrewService {

    @Autowired
    private CrewRepository crewRepository;

    /**
     * Retrieve all crew members for a specific movie.
     *
     * @param id_movie the ID of the movie
     * @return list of crew members for the specified movie
     */
    public List<Crew> getCrewsByMovie(Integer id_movie) {

        Movie movie = new Movie();
        movie.setId(id_movie);


        return crewRepository.findByMovie(movie);
    }
}