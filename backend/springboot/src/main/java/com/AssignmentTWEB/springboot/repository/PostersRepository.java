package com.AssignmentTWEB.springboot.repository;

import com.AssignmentTWEB.springboot.model.Posters;
import com.AssignmentTWEB.springboot.model.Movie;
import com.AssignmentTWEB.springboot.primarykey.PostersPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostersRepository extends JpaRepository <Posters, PostersPrimaryKey>{

    //find all countries where a film has been produced
    Posters findByMovie(Movie movie);
}
