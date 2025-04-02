package com.AssignmentTWEB.springboot.repository;


import com.AssignmentTWEB.springboot.model.Studio;
import com.AssignmentTWEB.springboot.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Integer> {

    List<Studio> findByMovie(Movie movie); //find all movie's studios

}
