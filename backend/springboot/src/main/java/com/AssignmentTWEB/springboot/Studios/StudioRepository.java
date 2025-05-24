package com.AssignmentTWEB.springboot.Studios;


import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

    List<Studio> findByMovie(Movie movie); //find all movie's studios

}
