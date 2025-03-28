package com.AssignmentTWEB.springboot.service;

import com.AssignmentTWEB.springboot.model.Posters;
import com.AssignmentTWEB.springboot.model.Movie;
import com.AssignmentTWEB.springboot.primarykey.PostersPrimaryKey;
import com.AssignmentTWEB.springboot.repository.PostersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostersService {

    @Autowired
    private PostersRepository postersRepository;

    //get all posters by an id_movie
    public List<String> getPostersByMovie(Integer id_movie) {
        Movie movie = new Movie();
        movie.setId_movie(id_movie);

        List<Posters> posters = postersRepository.findByMovie(movie);

        return posters.stream()
                .map(poster -> poster.getId_posters().getPosters())
                .collect(Collectors.toList());
    }
}
