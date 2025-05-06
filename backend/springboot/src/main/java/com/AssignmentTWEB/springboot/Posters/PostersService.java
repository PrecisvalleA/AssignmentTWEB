package com.AssignmentTWEB.springboot.Posters;

import com.AssignmentTWEB.springboot.Movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostersService {

    @Autowired
    private PostersRepository postersRepository;

    //get all posters by an id_movie
    public String getPostersByMovie(Integer id_movie) {
        Movie movie = new Movie();
        movie.setId_movie(id_movie);

        Posters posters = postersRepository.findByMovie(movie);

        if (posters != null && posters.getId_posters() != null) {
            return posters.getId_posters().getLink();
        } else {
            return null;
        }
    }
}