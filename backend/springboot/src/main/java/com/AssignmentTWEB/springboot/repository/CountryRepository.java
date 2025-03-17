package com.AssignmentTWEB.springboot.repository;

import com.AssignmentTWEB.springboot.model.Country;
import com.AssignmentTWEB.springboot.model.Movie;
import com.AssignmentTWEB.springboot.primarykey.CountryPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository <Country, CountryPrimaryKey>{

    //find all countries where a film has been produced
    List<Country> findByMovie(Movie movie);
}
