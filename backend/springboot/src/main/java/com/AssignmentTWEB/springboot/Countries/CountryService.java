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
    public List<String> getCountriesByMovie(Integer id_movie) {
        Movie movie = new Movie();
        movie.setId_movie(id_movie);

        List<Country> countries = countryRepository.findByMovie(movie);

        return countries.stream()
                .map(country -> country.getId_country().getCountry())
                .collect(Collectors.toList());
    }
}
