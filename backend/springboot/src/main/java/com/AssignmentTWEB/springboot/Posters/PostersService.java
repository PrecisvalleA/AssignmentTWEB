package com.AssignmentTWEB.springboot.Posters;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostersService {

    @Autowired
    private PostersRepository postersRepository;

    //get all posters by an id_movie
    public Posters getPostersByMovie(Integer id_movie) {
        Movie movie = new Movie();
        movie.setId_movie(id_movie);

        return postersRepository.findByMovie(movie);

    }
}