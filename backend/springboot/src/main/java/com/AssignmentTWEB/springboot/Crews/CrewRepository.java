package com.AssignmentTWEB.springboot.Crews;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CrewRepository extends JpaRepository<Crew, Long> {

    List<Crew> findByMovie(Movie movie); //find all movie's crew
}
