package com.AssignmentTWEB.springboot.Countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/movies/{id_movie}")
    public List<Country> getAllCountries(@PathVariable Integer id_movie) {
        return countryService.getCountriesByMovie(id_movie);
    }
}
