package com.AssignmentTWEB.springboot.Countries;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository <Country, CountryPrimaryKey>{

    //find all countries where a film has been produced
    List<Country> findByMovie(Movie movie);
}
