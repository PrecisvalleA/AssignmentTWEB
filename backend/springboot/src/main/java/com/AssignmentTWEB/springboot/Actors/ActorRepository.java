package com.AssignmentTWEB.springboot.Actors;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    List<Actor> findByMovie(Movie movie); //find all movie's actors
}
