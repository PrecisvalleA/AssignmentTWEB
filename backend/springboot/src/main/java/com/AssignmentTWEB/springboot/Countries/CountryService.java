package com.AssignmentTWEB.springboot.Countries;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for business logic related to Countries.
 */
@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    /**
     * Retrieve all countries associated with a specific movie.
     *
     * @param id the ID of the movie
     * @return list of countries related to the movie
     * @throws EntityNotFoundException if the movie is not found
     */
    public List<Country> getCountriesByMovie(Integer id) {
        Movie movie = new Movie();
        movie.setId(id);

        return countryRepository.findByMovie(movie);
    }
}
