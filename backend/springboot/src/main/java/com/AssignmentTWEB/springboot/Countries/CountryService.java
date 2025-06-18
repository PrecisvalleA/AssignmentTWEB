package com.AssignmentTWEB.springboot.Countries;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    //get all countries by an id_movie
    public List<Country> getCountriesByMovie(Integer id_movie) {
        Movie movie = new Movie();
        movie.setId(id_movie);

        return countryRepository.findByMovie(movie);
    }
}
