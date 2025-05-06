package com.AssignmentTWEB.springboot.Posters;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostersRepository extends JpaRepository <Posters, PostersPrimaryKey>{

    //find all countries where a film has been produced
    Posters findByMovie(Movie movie);
}
